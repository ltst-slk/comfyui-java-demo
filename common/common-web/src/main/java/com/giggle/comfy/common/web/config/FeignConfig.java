package com.giggle.comfy.common.web.config;

import feign.codec.Decoder;
import feign.optionals.OptionalDecoder;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.loadbalancer.core.DiscoveryClientServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

public class FeignConfig {
    @Bean
    public Decoder feignDecoder(ObjectProvider<HttpMessageConverters> messageConverters) {
        return new OptionalDecoder((new ResponseEntityDecoder(new FeignDecoder(new SpringDecoder(messageConverters)))));
    }

    @Bean
    @Primary
    public ReactorServiceInstanceLoadBalancer customLoadBalancer(
            ObjectProvider<ServiceInstanceListSupplier> supplierProvider,
            DiscoveryClient discoveryClient,
            Environment environment) {
        ServiceInstanceListSupplier supplier = supplierProvider.getIfAvailable(
                () -> getDiscoveryClientServiceInstanceListSupplier(discoveryClient, environment));

        return new GPURandomLoadBalancer(supplier);
    }

    private ServiceInstanceListSupplier getDiscoveryClientServiceInstanceListSupplier(
            DiscoveryClient discoveryClient,
            Environment environment){
        return new DiscoveryClientServiceInstanceListSupplier(discoveryClient, environment);
    }
}

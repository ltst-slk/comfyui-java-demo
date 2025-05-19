# comfyui-cloud 模块设计

## api
    用于接受外部服务请求 , 以及调用内部微服务

## common
    通用服务模块，封装 核心通用组件 包括 负载均衡策略 GPURandomLoadBalancer

    common-core 封装返回值以及系统参数
    common-web web、feign 相关配置，包括 负载均衡策略

## connect
    用于与 comfyui 进行通信 , 通信方式 使用 HTTP 访问 comfyUI 接口
    并统计 服务器 gpu 使用率
    通过定时任务上报 gpu 使用率
    服务启动时 启动 java 命令 调用命令实时获取并记录 当前服务器使用率

该服务整体基于 spring cloud alibaba 使用 nacos 作为注册配置中心 


# ComfyUI 安装配置文档

## ComfyUI env install & config

Step1 clone from github
git clone https://github.com/comfyanonymous/ComfyUI.git

Step2 create python venv
python3 -m venv ComfyUI
source ./bin/activate

Step3 amd GPU install
pip install torch torchvision torchaudio --index-url https://download.pytorch.org/whl/rocm6.2.4

Step4 cd home work
cd ComfyUI

Step5 install requirements
pip install -r requirements.txt

Step6 accept user use GPU
sudo usermod -a -G video,render $LOGNAME

Step7  --listen 0.0.0.0 accept other computer visit this server
HSA_OVERRIDE_GFX_VERSION=11.0.0 python3 main.py --listen 0.0.0.0



## ComfyUI_StoryDiffusion
1. git cloen
   git clone https://github.com/smthemex/ComfyUI_StoryDiffusion.git
2. cd ComfyUI_StoryDiffusion
   cd ComfyUI_StoryDiffusion
3. install
    pip install -r requirements.txt

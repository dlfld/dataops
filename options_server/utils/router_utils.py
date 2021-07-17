from fastapi import APIRouter


class Router:
    router = None


# 在项目中使用单例模式的router  这样就少了一轮的配置了
def get_router():
    if Router.router is None:
        Router.router = APIRouter()
    return Router.router

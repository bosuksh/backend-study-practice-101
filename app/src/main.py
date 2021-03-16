import uvicorn
from fastapi import FastAPI
from starlette import status

import external_interfaces
from container import Container
from external_interfaces.routers import router


def create_app() -> FastAPI:
    container = Container()
    container.wire(modules=[external_interfaces.routers])

    app = FastAPI()
    app.container = container
    app.include_router(router)
    return app


app = create_app()


@app.get("/health", status_code=status.HTTP_200_OK)
async def health():
    return {"msg": "I'm health!"}


if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=8000)

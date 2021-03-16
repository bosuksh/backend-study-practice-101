from dependency_injector.wiring import Provide, inject
from fastapi import APIRouter, Depends
from starlette import status

from container import Container
from services.create_post import CreatePostResponse, CreatePostRequest, CreatePost
from services.delete_post import DeletePostResponse
from services.read_posts import ReadPostsResponse, ReadPosts, ReadPostsRequest
from services.update_post import UpdatePostResponse, UpdatePostRequest, UpdatePost

router = APIRouter(
    prefix="/posts",
    tags=["posts"],
)


@router.get("/", status_code=status.HTTP_200_OK, response_model=ReadPostsResponse)
@inject
def read_posts(service: ReadPosts = Depends(Provide[Container.read_posts])):
    request = ReadPostsRequest()
    response = service.execute(request)
    return response


@router.post("/", status_code=status.HTTP_201_CREATED, response_model=CreatePostResponse)
@inject
def create_post(request: CreatePostRequest, service: CreatePost = Depends(Provide[Container.create_post])):
    response = service.execute(request)
    return response


@router.put("/{post_id}", status_code=status.HTTP_204_NO_CONTENT, response_model=UpdatePostResponse)
@inject
def update_post(request: UpdatePostRequest, service: UpdatePost = Depends(Provide[Container.update_post])):
    response = service.execute(request)
    return response


@router.delete("/{post_id}", status_code=status.HTTP_200_OK, response_model=DeletePostResponse)
@inject
def delete_post(request: UpdatePostRequest, service: UpdatePost = Depends(Provide[Container.update_post])):
    response = service.execute(request)
    return response

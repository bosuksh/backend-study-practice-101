import time
from dataclasses import dataclass

from domain.model import PostId, Post, PostTitle, AuthorId
from domain.repository import PostRepository
from services.abstract import Service, ServiceRequest, ServiceResponse


@dataclass
class UpdatePostRequest(ServiceRequest):
    post_id: str
    title: str
    author_id: str
    content: str


@dataclass
class UpdatePostResponse(ServiceResponse):
    pass


class UpdatePost(Service):
    def __init__(self, post_repository: PostRepository) -> None:
        self.post_repository = post_repository

    def execute(self, request: UpdatePostRequest) -> UpdatePostResponse:
        post = self.post_repository.find_by_id(PostId(request.post_id))
        post = Post(
            id=post.id,
            title=PostTitle(request.title),
            author_id=AuthorId(request.author_id),
            content=request.content,
            created_at=post.created_at,
            updated_at=int(time.time()),
        )
        self.post_repository.save(post)
        return UpdatePostResponse()

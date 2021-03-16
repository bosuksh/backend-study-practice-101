from dataclasses import dataclass

from domain.factory import PostFactory
from domain.repository import PostRepository
from services.abstract import Service, ServiceRequest, ServiceResponse


@dataclass
class CreatePostRequest(ServiceRequest):
    title: str
    author_id: str
    content: str


@dataclass
class CreatePostResponse(ServiceResponse):
    post_id: str


class CreatePost(Service):
    def __init__(self, post_repository: PostRepository) -> None:
        self.post_repository = post_repository
        self._post_factory = PostFactory(post_repository=post_repository)

    def execute(self, request: CreatePostRequest) -> CreatePostResponse:
        post = self._post_factory.create(title=request.title, author_id=request.author_id, content=request.content)
        self.post_repository.save(post)
        return CreatePostResponse(post_id=str(post.id))

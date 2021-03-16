from dataclasses import dataclass
from typing import List

from domain.model import Post
from domain.repository import PostRepository
from services.abstract import Service, ServiceRequest, ServiceResponse


@dataclass
class ReadPostsRequest(ServiceRequest):
    pass


@dataclass
class ReadPostsResponse(ServiceResponse):
    @dataclass
    class Item:
        id: str
        title: str
        author_id: str
        content: str
        created_at: int
        updated_at: int

        @classmethod
        def from_entity(cls, post: Post):
            return cls(
                id=str(post.id),
                title=str(post.title),
                author_id=str(post.author_id),
                content=str(post.content),
                created_at=post.created_at,
                updated_at=post.updated_at,
            )

    items: List[Item]


class ReadPosts(Service):
    def __init__(self, post_repository: PostRepository) -> None:
        self.post_repository = post_repository

    def execute(self, request: ReadPostsRequest) -> ReadPostsResponse:
        posts = self.post_repository.find_all()
        return ReadPostsResponse(items=[ReadPostsResponse.Item.from_entity(post) for post in posts])

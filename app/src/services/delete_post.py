from dataclasses import dataclass

from domain.model import PostId
from domain.repository import PostRepository
from services.abstract import Service, ServiceRequest, ServiceResponse


@dataclass
class DeletePostRequest(ServiceRequest):
    post_id: str


@dataclass
class DeletePostResponse(ServiceResponse):
    pass


class DeletePost(Service):
    def __init__(self, post_repository: PostRepository) -> None:
        self.post_repository = post_repository

    def execute(self, request: DeletePostRequest) -> DeletePostResponse:
        _ = self.post_repository.delete_by_id(PostId(request.post_id))
        return DeletePostResponse()

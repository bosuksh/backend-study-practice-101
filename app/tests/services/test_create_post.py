from pytest import fixture

from domain.model import PostId, Post, PostTitle, AuthorId
from services.create_post import CreatePostRequest, CreatePost, CreatePostResponse


@fixture
def create_post(post_repository):
    return CreatePost(post_repository=post_repository)


def test_create_post_success(create_post, post_repository):
    # given
    request = CreatePostRequest(title="title-1", author_id="author-1", content="content-1")
    actual = create_post.execute(request)
    expected = CreatePostResponse(post_id="0")

    # then (1) 출력 값이 맞는지 확인
    assert actual == expected

    # then (2) 실제로 업데이트 되어 저장되었는지 확인
    actual = post_repository.find_all()
    expected = [
        Post(
            id=PostId("0"),
            title=PostTitle("title-1"),
            author_id=AuthorId("author-1"),
            content="content-1",
        )
    ]
    assert actual == expected

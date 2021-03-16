from pytest import fixture

from domain.factory import PostFactory
from domain.model import Post, PostId, AuthorId, PostTitle
from services.update_post import UpdatePost, UpdatePostRequest, UpdatePostResponse


@fixture
def update_post(post_repository):
    return UpdatePost(post_repository=post_repository)


def test_update_post_success(update_post, post_repository):
    # given
    post_factory = PostFactory(post_repository)
    post = post_factory.create(title="title-1", author_id="author-1", content="content-1")
    post_repository.save(post)

    # when
    request = UpdatePostRequest(
        post_id="0", title="title-modified", author_id="author-modified", content="content-modified"
    )
    actual = update_post.execute(request)
    expected = UpdatePostResponse()

    # then (1) 출력 값이 맞는지 확인
    assert actual == expected

    # then (2) 실제로 업데이트 되어 저장되었는지 확인
    actual = post_repository.find_all()
    expected = [
        Post(
            id=PostId("0"),
            title=PostTitle("title-modified"),
            author_id=AuthorId("author-modified"),
            content="content-modified",
        )
    ]
    assert actual == expected

from pytest import fixture

from infra_structures.in_mem_repository import InMemPostRepository


@fixture
def post_repository():
    return InMemPostRepository()

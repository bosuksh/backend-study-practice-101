from dependency_injector import containers, providers

from infra_structures.in_mem_repository import InMemPostRepository
from services.create_post import CreatePost
from services.delete_post import DeletePost
from services.read_posts import ReadPosts
from services.update_post import UpdatePost


class Container(containers.DeclarativeContainer):
    post_repository = providers.Singleton(InMemPostRepository)

    create_post = providers.Singleton(CreatePost, post_repository=post_repository)
    delete_post = providers.Singleton(DeletePost, post_repository=post_repository)
    read_posts = providers.Singleton(ReadPosts, post_repository=post_repository)
    update_post = providers.Singleton(UpdatePost, post_repository=post_repository)

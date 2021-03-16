from abc import ABCMeta, abstractmethod
from typing import Optional


class ServiceRequest(metaclass=ABCMeta):
    pass


class ServiceResponse(metaclass=ABCMeta):
    pass


class Service(metaclass=ABCMeta):
    @abstractmethod
    def execute(self, request: ServiceRequest) -> Optional[ServiceResponse]:
        pass

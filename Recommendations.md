This is a simple application created using in memory database in interest of time.

I would recommend the following pointers to be added:
  - Each function like credit/debit/reversal should ideally be a microservice
  - Choosing which fucntion to behave as an indivdual microservice depends on the NFR(Non-functional Requirements) and other KPI.
  - Having each function as a microservice makes it easier to scale up when required.
  - From an orchestration level, we need to add security in our headers to understand the request comes through an authenticated and
  authorized service. This can be achieved in many ways and it also depends how API gateways are used in a particular organization.
  One way to achieve is to configure clientId level access to that service.

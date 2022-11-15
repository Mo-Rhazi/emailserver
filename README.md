# Product-development-Project
a REST API to execute CRUD  operations.

Technical test
The objective of this test is to create a service that exposes a REST API to execute CRUD 
operations. The general use case is to replicate the behavior or an email server.
Endpoints should be under the route /emails and the information corresponding to the email
contents should be stored in a database.
Main Requirements
1. An email could have only the following states:
a. DRAFT
b. SENT
c. DELETED
d. SPAM
2. Apart from the basic email information, such as from, to, etc., update dates should be also 
stored in the database.
3. For inserting, query and delete the option for bulk operation should be available in the API
4. Only DRAFT emails could be updated (the content of the message, for instance)
5. Every day at 10:00, all the messages containing the address carl@gbtec.com should be 
marked as SPAM
Technologies
The technology should be Java 11 or newer.
Usage of high-level frameworks like Spring are highly recommended. 
Other
Code should be created in English (variables, comments, documentation, installation 
instructions, etc.).
Code shall be delivered on a repository on GitHub or a similar service.
Depending on the database technology, usage of Flyway/Liquibase or equivalent is an option.
Documentation shall outline relevant design and technology decisions

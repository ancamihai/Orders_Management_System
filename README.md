# ORDERS MANAGEMENT SYSTEM FOR A WAREHOUSE
## DESCRIPTION
$~~~$ A **Java Desktop** application _Orders Management_ for processing client orders for a _warehouse_. _Relational databases_ are used to store the _products_, the _clients_, and the _orders_. \
$~~~$ It has a _graphical user interface_ which includes:
* A window for **client operations**: _add new client_, _edit client_, _delete client_, _view all clients_ in a table (_JTable_)
* A window for **product operations**: _add new product_, _edit product_, _delete product_, _view all products_ in a table (_JTable_)
* A window for **creating product orders**: \
       - the user can **select** an _existing product_, select an _existing client_, and insert a _desired quantity_ for the product to create a valid order. \
       - when a _valid order_ is _confirmed_, a **bill** with the order's details is generated and displayed.

$~~~$ In case there are _not enough products_, an **under-stock message** will be displayed. \
$~~~$ After the order is finalized, the product stock is decremented. 

$~~~$ In addition, the application allows an employee:
* to **cancel an order**
* to **view all orders** that have been made until present moment
* to **view all bills** that have been generated until present moment

  ## STRUCTURE
$~~$  **OOP** design of the application - The project is based on a _layered architecture_, which has the following advantages:
  1. **Code Maintenance** is easy: we can easily determine any kind of change in the code
  2. **Security**: the data-providing package isn’t affected by the other packages
  3. **Ease of development**: building time taken by the application will be small as all the layers can work together at the same time     
&nbsp;

$~~$ The project has 5 packages + the _App class_ (its purpose is to run the application):
  1. **Model** – contains the classes _modeling the application data_ \
$~~~~~~~~~~~~~$- in this app: the **Bill**, **Client**, **Order** and **Product** classes
  2. **Presentation** – contains the classes implementing the _graphical user interface_
  3. **Business Logic** – contains the classes that _get the inputs of the graphical user interface_ and _process them accordingly_ (_alter_ the data of the tables of the _database_ based on what was introduced)
  4. **Data Access** - contains the classes containing the _queries for the database_
  5. **Connection** – contains the _database connection_

&nbsp;

**FULL DOCUMENTATION OF THE PROJECT** - _Documentation.pdf_

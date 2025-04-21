Salary Management System





Payroll Calculation Process


The Salary Management System implements a comprehensive payroll calculation process that automatically computes employee compensation based on multiple factors. Here's how the system calculates salary:

Core Components

1.Base Salary Retrieval

The system retrieves the employee's base salary from their profile in the database
This serves as the foundation for all payroll calculations


2.Overtime Calculation

Overtime hours are tracked in the system with their respective rates
The formula used: Total Overtime Pay = Sum of (Overtime Hours × Overtime Rate) for the pay period
Implementation is in the calculateOvertime() method in PayRollServiceImpl


3.Leave Deduction Processing

The system tracks all approved leave requests
Leave deductions are calculated based on leave type and company policy
Unpaid leave directly affects the final salary calculation
Implementation is in the calculateLeaveDeductions() method



4.Net Salary Computation

The final formula: Net Salary = Base Salary + Overtime Pay + Bonus - Leave Deductions
Each component is calculated independently before being combined for the final amount



System Architecture
The payroll calculation system follows a layered architecture:

1.Controller Layer (PayRollController) - Handles HTTP requests and responses
2.Service Layer (PayRollService, PayRollServiceImpl) - Contains business logic
3.Repository Layer (PayRollRepository) - Handles database operations
4.Entity/DTO Layer (Payroll, PayrollDTO) - Represents data structures





Technologies Used

1.Spring Boot
2.JPA/Hibernate
3.JWT for authentication
4.ModelMapper for entity-DTO conversion
5.RESTful API design




YOU TUBE LINK: https://youtu.be/45Ic15XJLqk?si=vG9hc8P98NJqrBlu











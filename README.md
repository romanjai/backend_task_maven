# Loans apply and approve in microservice

## Compiling

Service use maven
Compile with maven: `mvn clean install`

## Running

 - Run with Launcher
 
loan preparator is user id.
Some default value for
loans:
            
            interest: 5.00
            factor: 1.50
            manager for Bulgaria: Roman
            manager for Moldova: Igor
            manager for Latvia: Ivar

## Configuration

Config file is `application.yml` in `src/main/resources`
      

## REST endpoints

#####Loan history:

GET    `/rest/loans`

#####Apply for a loan:

POST   `/rest/loans` 

    params:
        amount: 700.00
        customer id: 10
    
#####Extend loan:
    
POST   `/rest/loans/{loanId}/extend`

#####Approve loan:
    
POST   `/rest/loans/{loanId}/approve`

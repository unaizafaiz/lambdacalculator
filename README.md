# Lambda Calculator
#### By Unaiza Faiz(651052450)
### Description: 
Created a calculator lambda function that adds, subtracts, multiplies and divides two operators.
Deployed the lamda function on AWS and built a RESTful API for the the calculator lambda function using AWS API Gateway.

## To clone the repository
`` git clone git@bitbucket.org:unaizafaiz/unaiza_faiz_hw6.git``

### Steps to invoke the RESTful API
1. Open browser
2. Type in the URL "https://tfepd2yxg0.execute-api.us-east-1.amazonaws.com/test/calculator"
3. In order to perform an operation 3 arguments need to be passed: op1 (operator1), op2 (operator2) and opnd (operand)
4. Example:
	- Subtract: https://tfepd2yxg0.execute-api.us-east-1.amazonaws.com/test/calculator?op1=60&op2=256&opnd=-
	- Multiply: https://tfepd2yxg0.execute-api.us-east-1.amazonaws.com/test/calculator?op1=60&op2=256&opnd=*
	- Divide: https://tfepd2yxg0.execute-api.us-east-1.amazonaws.com/test/calculator?op1=60&op2=256&opnd=/
	- Also checks for divide by zero error https://tfepd2yxg0.execute-api.us-east-1.amazonaws.com/test/calculator?op1=60&op2=0&opnd=/
	- Add operation: https://tfepd2yxg0.execute-api.us-east-1.amazonaws.com/test/calculator?op1=60&op2=256&opnd=+

##### Challenges: 
The Add operation works when testing the gateway but does not recognize the operand using the URL! Please see [screenshot](./AddRESTCalc.png) attached. 
	


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<c:if test="${not empty ticket}">
		<h3>Your ticket number is ${ticket}</h3>
	</c:if>
	<c:if test="${not empty status}">
		<h3>Your ticket is status ${status}, number is ${ticketNum}
			departure time ${departDate} from city ${departCity}, arrival time
			${arrivalDate} to ${arrivalCity}</h3>
	</c:if>
	<c:if test="${cancelTicket=='true'}">
		<h3>The ticket with number ${ticketId} is successfully cancelled</h3>
	</c:if>
	<c:if test="${cancelTicket=='false'}">
		<h3>The ticket with number ${ticketId} isn't found</h3>
	</c:if>

	<c:if test="${not empty mistake}">
		<h3>The ticket with number ${mistake} isn't found</h3>
	</c:if>
	<center>
		<form action="/JaxRsTicketService/getTicket">
			<h3>You may write only number!</h3>
			</br> <input type="text" name="ticketNum" required pattern="[0-9]" />
			<input type="submit" value="Get ticket">
		</form>
		<br />
		<form action="/JaxRsTicketService/pay">
			<input type="text" name="ticketNum" required pattern="[0-9]" />
			<input type="submit" value="Pay">
		</form>
		<br />
		<form action="/JaxRsTicketService/return">
			<input type="text" name="ticketNum" required pattern="[0-9]" />
			<input type="submit" value="Return Ticket">
		</form>
	</center>
	<h2>Fill in the form</h2>
	<h4>Input your name, last name, patronymic name</h4>
	<form action="/JaxRsTicketService/reserve">
		<input type="text" name="name" required /> <input type="text"
			name="lastName" required /> <input type="text" name="patronymicName"
			required /> <input type="submit" value="Reserve" required /> </br>
		<h4>Input your birth date</h4>
		</br> <input type="date" name="birthDate" value="2015-01-01" required /></br>
		<h4>Input depart city and arrival city</h4>
		</br> <input type="text" name="departCity" required /> <input type="text"
			name="arrivalCity" /></br>
		<h4>Input depart day and arrive day(time) in format YYYY-MM-DD</h4>
		</br> <input type="date" name="departDate" value="2015-01-01" required />
		<input type="text" name="departTime" readonly value="08:55:00"
			required /></br> </br> <input type="date" name="arrivalDate"
			value="2015-01-01" required /> <input type="text" name="arrivalTime"
			readonly value="08:55:00" required />
	</form>

</body>
</html>

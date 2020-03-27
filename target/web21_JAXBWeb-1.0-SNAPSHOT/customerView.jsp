<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer View</title>
        
        <script src="customer.js" type="text/javascript"></script>
    </head>
    <body>
        <h1>Customer View</h1>
        
            <select id="countries" name="countries" onchange="customerRequest();">
                <c:forEach var="country" items="${countries}">
                    <option>${country.name}</option>
                </c:forEach>
            </select>
        
        
            <table border = "1">
                <tbody id="representatives">
                    <tr>
                        <td>Kyle</td>
                        <td>
                            Customer 1 <br>
                            Customer 2 <br>
                            Customer 3 <br>
                        </td>
                    </tr>
                </tbody>
            </table>
    </body>
</html>

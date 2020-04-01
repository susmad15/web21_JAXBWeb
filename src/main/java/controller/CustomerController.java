package controller;

import data.Countries;
import data.Country;
import data.Customer;
import data.Representative;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXB;
import server.IServerProxy;
import server.ServerProxyFactory;

@WebServlet(name = "CustomerController", urlPatterns = {"/CustomerController"})
public class CustomerController extends HttpServlet {

    private IServerProxy server = ServerProxyFactory.getInstance();
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        System.out.println("CustomerController.init: " + System.getProperty("user.dir"));

        String path = config.getServletContext().getRealPath("res/countries.xml");
        System.out.println("CustomerController.init: " + path);

        File file = new File(path);

        if (file.exists()) {
            System.out.printf("CustomerController.init: file %s exists\n", path);
        }

        Countries countries = (Countries) JAXB.unmarshal(new File(path), Countries.class);

        JAXB.marshal(countries, System.out);
        
       // Remove this after first run
       // Then set to none
        server.persistCountries(countries);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Country> countries = server.getAllCountries();

        for (Country country : countries) {
            System.out.println("processRequest: " + country.toString());
        }

        request.setAttribute("countries", countries);

        request.getRequestDispatcher("customerView.jsp").forward(request, response);
        
        //processRequest(request, response);   
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        System.out.println("CustomerController: POST");
        
        String bodyText = 
                new BufferedReader(
                new InputStreamReader(request.getInputStream()))
                .readLine();
        
        System.out.println("doBody: bodyText: " + bodyText);
        
        List<Representative> reps = server.getRepresentativesOfCountry(bodyText);
        String result = "";
        for (Representative rep : reps) {
            System.out.println("doPost: RepName: " + rep.getName());
            result += "<tr><td>" + rep.getName() + "</td>";
            result += "<td>";
            for(Customer customer : rep.getCustomers()){
                result += customer.getName() + "<br/>";
                System.out.println("doPost CustomerName: " + customer.getName());
            }
        }
        
        result += "</tr>";
        
        OutputStreamWriter out = 
            new OutputStreamWriter(response.getOutputStream());
        
        out.write(result);
        
        out.flush();
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

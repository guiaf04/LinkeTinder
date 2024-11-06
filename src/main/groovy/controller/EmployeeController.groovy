package controller

import com.google.gson.Gson
import dao.EmployeeDAO
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import model.Empresa
import service.EmployeeService

class EmployeeController extends HttpServlet{
    EmployeeService employeeService = new EmployeeService(employeeDAO: new EmployeeDAO())

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Empresa> jsonText = employeeService.listEmployees()

        Gson gson = new Gson()
        String jsonResult = "["

        jsonText.forEach {
            jsonResult += gson.toJson(it)
            jsonResult += ","
        }

        jsonResult += "]"
        jsonResult = jsonResult.replace(/,]/, ']')
        resp.getWriter().write(jsonResult)
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader bufferedReader = req.getReader()

        Gson gson = new Gson()
        Empresa empresa = gson.fromJson(bufferedReader, Empresa.class)

        if (employeeService.addEmployee(empresa)){
            resp.setStatus(HttpServletResponse.SC_CREATED)
        }else{
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST)
        }
    }
}

package controller

import com.google.gson.Gson
import dao.VacancyDAO
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import model.Competencia
import model.Vaga
import service.VacancyService

class VacancyController extends HttpServlet{
    VacancyService vacancyService = new VacancyService(vacancyDAO: new VacancyDAO())

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Vaga> jsonText = vacancyService.listVacancys()

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

        Vaga vaga = gson.fromJson(bufferedReader, Vaga)

        if (vacancyService.addVacancy(vaga)){
            resp.setStatus(HttpServletResponse.SC_CREATED)
        }else{
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST)
        }

    }

    List<String> showVacancies(){
        return vacancyService.listVacancys()
    }

    boolean addVacancy(Vaga vaga){
        return vacancyService.addVacancy(vaga)
    }

    boolean editVacancy(Vaga vaga){
        return vacancyService.editVacancy(vaga)
    }

    boolean deleteVacancy(Vaga vaga){
        return vacancyService.deleteVacancy(vaga)
    }
}

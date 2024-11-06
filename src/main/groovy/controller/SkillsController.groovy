package controller

import com.google.gson.Gson
import dao.SkillsDAO
import jakarta.servlet.ServletException
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import model.Candidato
import model.Competencia
import service.SkillsService

@WebServlet()
class SkillsController extends HttpServlet{
    SkillsService skillsService = new SkillsService(skillsDAO: new SkillsDAO())

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Candidato> jsonText = skillsService.listSkills()

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
        super.doPost(req, resp)
    }

    List<Competencia> showSkills(){
        return skillsService.listSkills()
    }

    boolean addSkills(Competencia competencias){
        return skillsService.addSkills(competencias)
    }

    boolean editSkills(Competencia competencias){
        return skillsService.editSkills(competencias)
    }

    boolean deleteSkills(Competencia competencias){
        return skillsService.deleteSkills(competencias)
    }
}

package controller

import com.google.gson.Gson
import dao.SkillsDAO
import jakarta.servlet.ServletException
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import model.Competencia
import service.SkillsService

@WebServlet()
class SkillsController extends HttpServlet{
    SkillsService skillsService = new SkillsService(skillsDAO: new SkillsDAO())

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Competencia> jsonText = skillsService.listSkills()

        Gson gson = new Gson()
        String jsonResult = gson.toJson(jsonText)

        resp.getWriter().write(jsonResult)
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp)
    }

}

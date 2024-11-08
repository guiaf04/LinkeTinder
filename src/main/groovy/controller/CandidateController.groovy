package controller

import com.google.gson.Gson
import dao.CandidateDAO
import jakarta.servlet.ServletException
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import model.Candidato
import model.Competencia
import service.CandidateService

@WebServlet(urlPatterns = ["/candidatos", "/candidatos/competencias/*"])
class CandidateController extends HttpServlet{
    CandidateService candidateService = new CandidateService(candidateDAO: new CandidateDAO())

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getServletPath().equalsIgnoreCase("/candidatos")){
            List<Candidato> jsonText = candidateService.listCandidates()

            Gson gson = new Gson()
            String jsonResult = gson.toJson(jsonText)
            resp.getWriter().write(jsonResult)

            return
        }

        resp.getWriter().write("Testando conexão")
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getServletPath() == "/candidatos"){
            BufferedReader bufferedReader = req.getReader()

            Gson gson = new Gson()
            Candidato candidato = gson.fromJson(bufferedReader, Candidato.class)

            if (candidateService.addCandidate(candidato)){
                resp.setStatus(HttpServletResponse.SC_CREATED)
            }else{
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            }
        }else if (req.getServletPath() == "/candidatos/competencias"){
            int candidatoId = Integer.parseInt(req.getPathInfo().substring(1))

            BufferedReader bufferedReader = req.getReader()

            Gson gson = new Gson()
            List<Competencia> competencias = gson.fromJson(bufferedReader, List<Competencia>)

            if (candidateService.addSkills(competencias, candidatoId)){
                resp.setStatus(HttpServletResponse.SC_CREATED)
            }else{
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp)
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp)
    }
}

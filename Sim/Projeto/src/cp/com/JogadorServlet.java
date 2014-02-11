package cp.com;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class JogadorServlet extends HttpServlet {

	/* Funcao para cadastrar jogador */
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Gson g = new Gson();
		String data = req.getParameter("data");
		Jogador j = g.fromJson(data, Jogador.class);
		resp.setContentType("text/plain");
		JDOUtils jdo = new JDOUtils();

		jdo.save(j);
		resp.getWriter().println(g.toJson(j));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Gson g = new Gson();
		String data = req.getParameter("data");
		Jogador j = g.fromJson(data, Jogador.class);
		resp.setContentType("text/plain");
		JDOUtils jdo = new JDOUtils();

		List<Jogador> js = jdo.findByAttribute(Jogador.class, "nome",
				j.getNome());

		Jogador j2 = js.get(0);
		/*
		 * j2.setNome(j.getNome()); j2.setPosicao(j.getPosicao());
		 * j2.setQuantVotos(j.getQuantVotos() + 1);
		 * 
		 * jdo.delete(j); jdo.save(j2);
		 */
		j2.setQuantVotos(j2.getQuantVotos() + 1);
		resp.getWriter().println(g.toJson(j2));
		resp.getWriter().println(
				g.toJson("Atualizacao realizada com sucesso !"));
	}

}

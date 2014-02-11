package cp.com;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class JogadoresServlet extends HttpServlet {

	/* Funcao para retornar o jogador mais mais votado */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Gson g = new Gson();
		resp.setContentType("text/plain");
		JDOUtils jdo = new JDOUtils();

		List<Jogador> js = jdo.findAll(Jogador.class);
		Jogador jPrimeiro = new Jogador();
		jPrimeiro.setQuantVotos(0);

		for (int i = 0; i < js.size(); i++) {
			if (js.get(i).getQuantVotos() > jPrimeiro.getQuantVotos()) {
				jPrimeiro.setNome(js.get(i).getNome());
				jPrimeiro.setPosicao(js.get(i).getPosicao());
				jPrimeiro.setQuantVotos(js.get(i).getQuantVotos());
			}
		}
		resp.getWriter().println(g.toJson(jPrimeiro));
	}
}

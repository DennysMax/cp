package cp.com;

import java.io.IOException;

import javax.servlet.http.*;

//RECAPTCHA
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

@SuppressWarnings("serial")
public class PostFormDataServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		String strResponse = "";
		try {

			/*
			 * CAPTCHA CHECK
			 */
			// First validate the captcha, if not -- just get out of the loop
			String challenge = (String) req
					.getParameter("recaptcha_challenge_field");
			String response = (String) req
					.getParameter("recaptcha_response_field");
			if ((challenge == null) || (response == null)) {
				throw new Exception(
						"Suas palavras não coincidem. Tente enviar novamente.");
			}

			String remoteAddr = req.getRemoteAddr();
			ReCaptchaImpl reCaptcha = new ReCaptchaImpl();

			reCaptcha.setPrivateKey("6LfcRu4SAAAAAJtbyzRRyU5bdlPlvZtWYcaGSqK0");

			ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(
					remoteAddr, challenge, response);

			if (!reCaptchaResponse.isValid()) {
				// RECAPTCHA VALIDATION FAILED
				throw new Exception(
						"Suas palavras nao coincidem. Tente enviar novamente.");
			}			
			strResponse = "Seu registro foi aceito, e voce fez um bom trabalho de entrar as duas palavras. Obrigado";
		} catch (Exception ex) {
			strResponse = "Seu registro nao foi aceito Motivo:"
					+ ex.getMessage();
		}
		resp.getWriter().println(strResponse);
	}
}
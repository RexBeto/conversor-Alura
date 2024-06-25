import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;

public class consultaAPI {

    public static void convertirMoneda(String monedaOrigen, String monedaSalida, double cantidad) {
        try {
            String direccion = "https://v6.exchangerate-api.com/v6/86071ba624a09415e7a8e1d7/latest/" + monedaOrigen;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(direccion))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


            String jsonResponse = response.body();

            Gson gson = new Gson();
            JsonObject json = gson.fromJson(jsonResponse, JsonObject.class);
            JsonObject rates = json.getAsJsonObject("conversion_rates");

            if (!rates.has(monedaSalida)) {
                throw new IllegalArgumentException("La moneda de salida no es válida.");
            }

            double tasaMonedaDestino = rates.get(monedaSalida).getAsDouble();
            double resultado = cantidad * tasaMonedaDestino;


            DecimalFormat df = new DecimalFormat("#.###");
            String resultadoFormateado = df.format(resultado);
            System.out.println(cantidad + " " + monedaOrigen + " equivale a " + resultadoFormateado + " " + monedaSalida);


        } catch (IOException | InterruptedException e) {
            System.out.println("Error al realizar la conversión: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
}

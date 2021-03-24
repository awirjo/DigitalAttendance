package sr.unasat.digitalattendance.restApi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sr.unasat.digitalattendance.R;

public class RestCall extends AppCompatActivity {
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_call);
        textViewResult = findViewById(R.id.text_view_result);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://0693d087-e579-47b4-ab85-d8b7290d7291.mock.pstmn.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonHolder jsonPlaceHolderApi = retrofit.create(JsonHolder.class);
        Call<List<RestUsers>> call = jsonPlaceHolderApi.getUsers();
        call.enqueue(new Callback<List<RestUsers>>() {
            @Override
            public void onResponse(Call<List<RestUsers>> call, Response<List<RestUsers>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                List<RestUsers> users = response.body();
                for (RestUsers restUsers : users) {
                    String content = "";
                    content += "First Name: " + restUsers.getFirstName() + "\n";
                    content += "Last Name: " + restUsers.getLastName() + "\n";
                    content += "Student Class: " + restUsers.getStudentClass() + "\n\n";
                    textViewResult.append(content);
                }
            }
            @Override
            public void onFailure(Call<List<RestUsers>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
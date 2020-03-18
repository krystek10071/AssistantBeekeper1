package com.example.assistantbeekeeper.severalDaysForecast.alertRSOFagment.FragmentActivity;

        import android.content.ClipData;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Toast;

        import com.example.assistantbeekeeper.R;
        import com.example.assistantbeekeeper.severalDaysForecast.alertRSOFagment.DataModel.AlertsWeatherData;
        import com.example.assistantbeekeeper.severalDaysForecast.alertRSOFagment.FragmentAlertsPre.AlertsPre;
        import com.example.assistantbeekeeper.severalDaysForecast.severalDaysActivity.FiveDaysForecastActivity;


        import java.util.ArrayList;

        import androidx.annotation.Nullable;
        import androidx.fragment.app.Fragment;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

public class FragmentActivity extends Fragment {

    private FragmentActivity listener;
    private RecyclerView recyclerView;
    private AlertsPre alertsPre=new AlertsPre();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ArrayList<AlertsWeatherData> articles=new ArrayList<>();
        ArrayList<String> listParameter=new ArrayList<>();

        //assigning layout to FragmentActivity
        assert inflater != null;
        View view= inflater.inflate(R.layout.alerts_forecast_fragment, container, false);
        recyclerView= view.findViewById(R.id.recycle_view);
        //optymalization
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        //set adapter
        recyclerView.setAdapter(new MyAdapter(articles));

        alertsPre.FethRSOData(this, listParameter);

        Toast.makeText(getActivity(), "Wybierz parametry do wyswietlenia", Toast.LENGTH_SHORT).show();
        return view;
    }


    public void setRecycleView(ArrayList<AlertsWeatherData> articles){
        recyclerView.setAdapter(new MyAdapter(articles));
    }

    public void displayMessage(){
        Toast.makeText(getActivity(), "Brak komunikatow RSO do wyswietlenia", Toast.LENGTH_LONG).show();
    }

    public void refreshRSOData(FiveDaysForecastActivity fiveDaysForecastActivity, ArrayList<String> listParameter){

        alertsPre.FethRSOData(this, listParameter);
    }

    //TODO
    //test method
    private void setArticles(ArrayList<AlertsWeatherData> articles){
        AlertsWeatherData obj1=new AlertsWeatherData("ostrze", "dasdasda","sdsda", "sdsd", "sdd");

        articles.add(obj1);
        articles.add(obj1);
        articles.add(obj1);
        articles.add(obj1);
        articles.add(obj1);

    }
}

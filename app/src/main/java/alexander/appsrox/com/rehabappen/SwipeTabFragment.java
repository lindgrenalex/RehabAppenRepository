package alexander.appsrox.com.rehabappen;

/**
 * Created by Alexander on 2015-11-27.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SwipeTabFragment extends Fragment {

    private String tab;
    private int color;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        tab = bundle.getString("tab");
        color = bundle.getInt("color");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment1, null);
        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText(tab);
        view.setBackgroundResource(color);
        return view;
    }
}
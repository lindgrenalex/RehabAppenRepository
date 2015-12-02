package alexander.appsrox.com.rehabappen;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class TabFragment1 extends Fragment implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    private int currentTemperature;
    private TextView txtSpeechInput, textViewTemperature;
    private Button temperatureUp, temperatureDown;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment_1, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tts = new TextToSpeech(getActivity(), this);

        currentTemperature = 25;
        textViewTemperature = (TextView) view.findViewById(R.id.currentTemperature);
        textViewTemperature.setText(currentTemperature + "°C");

        textViewTemperature.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });

        temperatureDown = (Button) view.findViewById(R.id.temperatureDown);
        temperatureDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentTemperature > 10) {
                    currentTemperature--;
                    tts.speak(currentTemperature + "grader", TextToSpeech.QUEUE_FLUSH, null);
                    textViewTemperature.setText(currentTemperature + "°C");
                } else {
                    tts.speak("Lägsta temperatur: 10 grader", TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });

        temperatureUp = (Button) view.findViewById(R.id.temperatureUp);
        temperatureUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentTemperature < 30) {
                    currentTemperature++;
                    tts.speak(currentTemperature + "grader", TextToSpeech.QUEUE_FLUSH, null);
                    textViewTemperature.setText(currentTemperature + "°C");
                } else {
                    tts.speak("Högsta temperatur: 30 grader", TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });

        txtSpeechInput = (TextView) view.findViewById(R.id.txtSpeechInput);


    }




    /**
     * Showing google speech input dialog
     */
    private void promptSpeechInput() {

        tts.speak("Vardagsrum: " + currentTemperature + " grader", TextToSpeech.QUEUE_FLUSH, null);
        /*
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            //Toast.makeText(getApplicationContext(), getString(R.string.speech_not_supported), Toast.LENGTH_SHORT).show();
        }
        */
    }

    /**
     * Receiving speech input
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == getActivity().RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtSpeechInput.setText(result.get(0));
                }
                break;
            }
        }
    }

    @Override
    public void onInit(int status) {
        tts.setLanguage(new Locale("sv", "SE"));
        tts.setSpeechRate((float) 0.9);
    }

}

package sg.edu.rp.c346.simpletodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button addBtn, clearBtn, deleteBtn;
    ListView listView;
    EditText taskInput;
    Spinner spinner;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> taskList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        taskInput = findViewById(R.id.taskInput);
        addBtn = findViewById(R.id.addBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        clearBtn = findViewById(R.id.clearBtn);
        listView = findViewById(R.id.listView);
        spinner = findViewById(R.id.spinner);
        arrayAdapter  = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, taskList);
        listView.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    case 0:
                        addBtn.setEnabled(true);
                        deleteBtn.setEnabled(false);
                        taskInput.setHint("Type in a new task here");
                        break;
                    case 1:
                        addBtn.setEnabled(false);
                        deleteBtn.setEnabled(true);
                        taskInput.setHint("Type the index of the task to be removed");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (taskList.size() > 0) {
                    if ((taskInput.getText().toString().matches("\\d")) && taskList.size() > Integer.parseInt(taskInput.getText().toString())) {
                        taskList.remove(Integer.parseInt(taskInput.getText().toString()));
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid input", Toast.LENGTH_SHORT).show();
                    }
                    //taskList.remove(0);
                    arrayAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                }
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!taskInput.getText().toString().isEmpty()) {
                    taskList.add(taskInput.getText().toString());
                } else {
                    Toast.makeText(MainActivity.this, "Please enter task name", Toast.LENGTH_LONG).show();
                }
                arrayAdapter.notifyDataSetChanged();
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskList.clear();
                arrayAdapter.notifyDataSetChanged();
           }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                taskList.remove(position);
//                arrayAdapter.notifyDataSetChanged();
                  Toast.makeText(MainActivity.this, "Position is updated for delete", Toast.LENGTH_LONG).show();
                  spinner.setSelection(1);
                  taskInput.setText(position+"");
            }
        });
    }
}

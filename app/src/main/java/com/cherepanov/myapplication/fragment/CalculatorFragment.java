package com.cherepanov.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cherepanov.myapplication.R;

/**
 * Created by Денис on 22.09.2017.
 */

public class CalculatorFragment extends Fragment {

    private Button zeroBtn;
    private Button oneBtn;
    private Button twoBtn;
    private Button threeBtn;
    private Button fourBtn;
    private Button fiveBtn;
    private Button sixBtn;
    private Button sevenBtn;
    private Button eightBtn;
    private Button nineBtn;
    private Button dotBtn;
    private Button addBtn;
    private Button subBtn;
    private Button divBtn;
    private Button multiBtn;
    private Button resultBtn;
    private ImageButton deleteBtn;
    private Button clearBtn;
    private EditText resultEdt;
    private TextView resultInfoTV;
    private View view;

    private double valueOne;
    private double valueTwo;
    private boolean isAdd;
    private boolean isSub;
    private boolean isMulti;
    private boolean isDiv;

    public static CalculatorFragment getInstance() {
        Bundle args = new Bundle();
        CalculatorFragment fragment = new CalculatorFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.calculator_layout, container, false);
        resultInfoTV = view.findViewById(R.id.info_tv);
        resultEdt = view.findViewById(R.id.edit_text);
        initButtons();
        setupListener();

        return view;
    }

    private void setupListener() {
        zeroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultEdt.setText(resultEdt.getText() + "0");
            }
        });
        oneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultEdt.setText(resultEdt.getText() + "1");
            }
        });
        twoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultEdt.setText(resultEdt.getText() + "2");
            }
        });
        threeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultEdt.setText(resultEdt.getText() + "3");
            }
        });
        fourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultEdt.setText(resultEdt.getText() + "4");
            }
        });
        fiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultEdt.setText(resultEdt.getText() + "5");
            }
        });
        sixBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultEdt.setText(resultEdt.getText() + "6");
            }
        });
        sevenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultEdt.setText(resultEdt.getText() + "7");
            }
        });
        eightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultEdt.setText(resultEdt.getText() + "8");
            }
        });
        nineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultEdt.setText(resultEdt.getText() + "9");
            }
        });
        dotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultEdt.setText(resultEdt.getText() + ".");
            }
        });
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultEdt.setText("");
            }
        });
        initOperations();
    }

    private void initOperations() {
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (resultEdt == null) {
                    resultEdt.setText("");
                } else {
                    valueOne = Double.valueOf(resultEdt.getText().toString());
                    isAdd = true;
                    resultInfoTV.setText(resultEdt.getText() + " + ");
                    resultEdt.setText(null);
                }
            }
        });
        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valueOne = Double.valueOf(resultEdt.getText().toString());
                isSub = true;
                resultInfoTV.setText(resultEdt.getText() + " - ");
                resultEdt.setText(null);
            }
        });
        multiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valueOne = Double.valueOf(resultEdt.getText().toString());;
                isMulti = true;
                resultInfoTV.setText(resultEdt.getText() + " * ");
                resultEdt.setText(null);
            }
        });
        divBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valueOne = Double.valueOf(resultEdt.getText().toString());
                isDiv = true;
                resultInfoTV.setText(resultEdt.getText() + " / ");
                resultEdt.setText(null);
            }
        });
        resultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valueTwo = Double.valueOf(resultEdt.getText().toString());
                resultInfoTV.setText(resultInfoTV.getText() + "" + valueTwo);

                double result;
                if (isAdd) {
                    result = valueOne + valueTwo;
                    resultEdt.setText(result + "");
                    isAdd = false;
                }
                if (isSub) {
                    result = valueOne - valueOne;
                    resultEdt.setText(result + "");
                    isSub = true;
                }
                if (isMulti) {
                    result = valueOne * valueTwo;
                    resultEdt.setText(result + "");
                    isMulti = true;
                }
                if (isDiv) {
                    result = valueOne / valueTwo;
                    resultEdt.setText(result + "");
                    isDiv = true;
                }
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultEdt.setText(resultEdt.getText().toString().isEmpty()
                        ? "" : resultEdt.getText().toString()
                        .substring(0, resultEdt.getText().toString().length() - 1));
            }
        });
    }

    private void initButtons() {
        zeroBtn = view.findViewById(R.id.zero_btn);
        oneBtn = view.findViewById(R.id.one_btn);
        twoBtn = view.findViewById(R.id.two_btn);
        threeBtn = view.findViewById(R.id.three_btn);
        fourBtn = view.findViewById(R.id.four_btn);
        fiveBtn = view.findViewById(R.id.five_btn);
        sixBtn = view.findViewById(R.id.six_btn);
        sevenBtn = view.findViewById(R.id.seven_btn);
        eightBtn = view.findViewById(R.id.eight_btn);
        nineBtn = view.findViewById(R.id.nine_btn);
        dotBtn = view.findViewById(R.id.dot_btn);
        addBtn = view.findViewById(R.id.add_btn);
        subBtn = view.findViewById(R.id.sub_btn);
        divBtn = view.findViewById(R.id.div_btn);
        multiBtn = view.findViewById(R.id.multi_btn);
        resultBtn = view.findViewById(R.id.result_btn);
        deleteBtn = view.findViewById(R.id.delete_btn);
        clearBtn = view.findViewById(R.id.clear_btn);
    }
}
package com.samsung;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Level5 extends AppCompatActivity {


    Dialog dialog;
    Dialog dialogEnd;


    public int numLeft;
    public int numRight;
    public int count = 0;


    Array array = new Array();
    Random random = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);


        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level5);


        final ImageView img_left = (ImageView) findViewById(R.id.img_left);
        img_left.setClipToOutline(true);


        final ImageView img_right = (ImageView) findViewById(R.id.img_right);
        img_right.setClipToOutline(true);


        final TextView text_left = findViewById(R.id.text_left);
        final TextView text_right = findViewById(R.id.text_right);


        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.preview_dialog_level5);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);


        TextView button_close = (TextView) dialog.findViewById(R.id.btn_close);
        button_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent = new Intent(Level5.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
                dialog.dismiss();

            }
        });

        Button button_continue = (Button) dialog.findViewById(R.id.button_continue);
        button_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

            }
        });

        dialog.show();


        dialogEnd = new Dialog(this);
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEnd.setContentView(R.layout.end_dialog_level5);
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogEnd.setCancelable(false);


        TextView button_close2 = (TextView) dialogEnd.findViewById(R.id.btn_close);
        button_close2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent = new Intent(Level5.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
                dialogEnd.dismiss();

            }
        });

        Button button_continue2 = (Button) dialogEnd.findViewById(R.id.button_continue);
        button_continue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level5.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }


                dialogEnd.dismiss();

            }
        });


        Button button_back = (Button) findViewById(R.id.button_back3);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level5.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }
        });


        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5
        };

        final Animation a = AnimationUtils.loadAnimation(Level5.this, R.anim.alfa);


        numLeft = random.nextInt(10);
        img_left.setImageResource(array.images5[numLeft]);
        text_left.setText(array.texts5[numLeft]);


        numRight = random.nextInt(10);


        while (numLeft == numRight) {
            numRight = random.nextInt(10);
        }


        img_right.setImageResource(array.images5[numRight]);
        text_right.setText(array.texts5[numRight]);

        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    img_right.setEnabled(false);
                    if (numLeft > numRight) {
                        img_left.setImageResource(R.drawable.img_correct);
                    } else {
                        img_left.setImageResource(R.drawable.img_incorrect);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (numLeft > numRight) {
                        if (count < 5) {
                            count = count + 1;
                        }


                        for (int i = 0; i < 5; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    } else {
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count = count - 2;
                            }
                        }
                        for (int i = 0; i < 4; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    }

                    if (count == 5) {
                        dialogEnd.show();

                    } else {
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images5[numLeft]);
                        img_left.startAnimation(a);
                        text_left.setText(array.texts5[numLeft]);


                        numRight = random.nextInt(10);


                        while (numLeft == numRight) {
                            numRight = random.nextInt(10);
                        }


                        img_right.setImageResource(array.images5[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts5[numRight]);

                        img_right.setEnabled(true);
                    }


                }
                return true;
            }
        });


        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    img_left.setEnabled(false);
                    if (numLeft < numRight) {
                        img_right.setImageResource(R.drawable.img_correct);
                    } else {
                        img_right.setImageResource(R.drawable.img_incorrect);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (numLeft < numRight) {
                        if (count < 5) {
                            count = count + 1;
                        }


                        for (int i = 0; i < 5; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    } else {
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count = count - 2;
                            }
                        }
                        for (int i = 0; i < 4; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    }

                    if (count == 5) {
                        dialogEnd.show();

                    } else {
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images5[numLeft]);
                        img_left.startAnimation(a);
                        text_left.setText(array.texts5[numLeft]);


                        numRight = random.nextInt(10);


                        while (numLeft == numRight) {
                            numRight = random.nextInt(10);
                        }


                        img_right.setImageResource(array.images5[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts5[numRight]);

                        img_left.setEnabled(true);
                    }


                }
                return true;
            }
        });


    }
}
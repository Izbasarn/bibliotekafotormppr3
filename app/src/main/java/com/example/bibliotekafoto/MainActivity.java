package com.example.bibliotekafoto;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private String[] imageUrls = {
            "https://sportishka.com/uploads/posts/2022-11/1667442747_64-sportishka-com-p-nishida-voleibol-instagram-65.jpg",
            "https://sportishka.com/uploads/posts/2022-11/1667442672_17-sportishka-com-p-nishida-voleibol-instagram-18.jpg",
            "https://sportishka.com/uploads/posts/2022-11/1667510177_61-sportishka-com-p-ishikava-voleibol-vkontakte-64.jpg",
            "https://sportishka.com/uploads/posts/2022-08/1660196292_26-sportishka-com-p-voleibol-suret-sport-krasivo-foto-27.jpg"

    };
    private int currentImageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        // Загрузка изображения по умолчанию
        loadImageWithAnimation(imageUrls[currentImageIndex]);

        // Добавление слушателя клика для изменения изображения
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Циклическое изменение изображения при каждом клике
                currentImageIndex = (currentImageIndex + 1) % imageUrls.length;
                loadImageWithAnimation(imageUrls[currentImageIndex]);
            }
        });
    }

    private void loadImageWithAnimation(String imageUrl) {
        Picasso.get().load(imageUrl).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                // Запуск анимации после успешной загрузки изображения
                ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f).setDuration(1000);
                ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(imageView, "scaleX", 0.5f, 1f).setDuration(1000);
                ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(imageView, "scaleY", 0.5f, 1f).setDuration(1000);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playSequentially(alphaAnimator, scaleXAnimator, scaleYAnimator);
                animatorSet.start();
            }

            @Override
            public void onError(Exception e) {
                // Обработка ошибок загрузки изображения
            }
        });
    }
}

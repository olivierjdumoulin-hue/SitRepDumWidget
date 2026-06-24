package fr.olivierdumoulin.sitrepdum;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.widget.RemoteViews;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class SitRepWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context,
                         AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {

        String texte = lireWidget();

        for (int appWidgetId : appWidgetIds) {

            RemoteViews views =
                new RemoteViews(context.getPackageName(),
                                R.layout.sitrep_widget);

            views.setTextViewText(R.id.widget_text, texte);

            Intent intent =
                new Intent(context, MainActivity.class);

            PendingIntent pendingIntent =
                PendingIntent.getActivity(
                    context,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT
                    | PendingIntent.FLAG_IMMUTABLE
                );

            views.setOnClickPendingIntent(
                R.id.widget_root,
                pendingIntent
            );

            appWidgetManager.updateAppWidget(
                appWidgetId,
                views
            );
        }
    }

    private String lireWidget() {

        try {

            File fichier = new File(
                Environment.getExternalStorageDirectory(),
                "Obsidian/ObsiDum/_INDEX/SitRepDum/widget.txt"
            );

            StringBuilder contenu =
                new StringBuilder();

            BufferedReader reader =
                new BufferedReader(
                    new FileReader(fichier)
                );

            String ligne;

            while ((ligne = reader.readLine()) != null) {
                contenu.append(ligne).append("\n");
            }

            reader.close();

            return contenu.toString();

        } catch (Exception e) {

            return "SitRepDum\n\nwidget.txt introuvable";
        }
    }

    public static void refresh(Context context) {

        Intent intent =
            new Intent(
                context,
                SitRepWidgetProvider.class
            );

        intent.setAction(
            AppWidgetManager.ACTION_APPWIDGET_UPDATE
        );

        int[] ids =
            AppWidgetManager
                .getInstance(context)
                .getAppWidgetIds(
                    new ComponentName(
                        context,
                        SitRepWidgetProvider.class
                    )
                );

        intent.putExtra(
            AppWidgetManager.EXTRA_APPWIDGET_IDS,
            ids
        );

        context.sendBroadcast(intent);
    }
}

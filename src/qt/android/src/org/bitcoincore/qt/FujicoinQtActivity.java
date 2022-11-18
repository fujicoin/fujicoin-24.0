package org.fujicoincore.qt;

import android.os.Bundle;
import android.system.ErrnoException;
import android.system.Os;

import org.qtproject.qt5.android.bindings.QtActivity;

import java.io.File;

public class FujicoinQtActivity extends QtActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        final File fujicoinDir = new File(getFilesDir().getAbsolutePath() + "/.fujicoin");
        if (!fujicoinDir.exists()) {
            fujicoinDir.mkdir();
        }

        super.onCreate(savedInstanceState);
    }
}

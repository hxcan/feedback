package com.stupidbeauty.qtdocchinese;

import android.content.Intent;


/**
 * @author root Hxcan <caihuosheng@gmail.com>
 *
 */
public class ArticleInfo
{
  private String packageName; //!<应用程序包名。

  public String getPackageName() {
    return packageName;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  private Intent launchIntent; //!<启动意图。

  public Intent getLaunchIntent() {
    return launchIntent;
  }

  public void setLaunchIntent(Intent launchIntent) {
    this.launchIntent = launchIntent;
  }

  private CharSequence applicationLabel; //!<应用程序名字标签。

  public CharSequence getApplicationLabel() {
    return applicationLabel;
  }

  public void setApplicationLabel(CharSequence applicationLabel) {
    this.applicationLabel = applicationLabel;
  }

}

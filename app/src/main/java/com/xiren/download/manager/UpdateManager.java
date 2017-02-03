package com.xiren.download.manager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;
import com.xiren.download.DownLoadService;
import com.xiren.download.R;
import com.xiren.download.Utils.DeviceUtils;
import com.xiren.download.Utils.ToastUtil;
import com.xiren.download.ui.dialog.OkDialog;

/**
 * Created by zs on 2016/7/7.
 */
public class UpdateManager {

    private Context mContext;

    public UpdateManager(Context context) {
        this.mContext = context;
    }
    /**
     * 检测软件更新
     */
    public void checkUpdate(final boolean isToast) {
        /**
         * 在这里请求后台接口，获取更新的内容和最新的版本号
         */
        // 版本的更新信息
        String version_info = "更新内容\n" + "    1. 车位分享异常处理\n" + "    2. 发布车位折扣格式统一\n" + "    ";
        int mVersion_code = DeviceUtils.getVersionCode(mContext);// 当前的版本号
        int nVersion_code = 2;
        if (mVersion_code < nVersion_code) {
            // 显示提示对话
            showNoticeDialog(version_info);
        }else{
            if (isToast){
                ToastUtil.showShortToast(mContext,"已经是最新版本");
            }
        }
    }

    /**
     * 显示更新对话框
     *
     * @param version_info
     */
    private void showNoticeDialog(String version_info) {

        OkDialog okDialog = new OkDialog(mContext, mContext.getString(R.string.is_update_version), new OkDialog.OnDialogListener() {
            @Override
            public void onDialogOK(OkDialog okDialog) {
                mContext.startService(new Intent(mContext, DownLoadService.class));
            }

            @Override
            public void onDialogCancel(OkDialog okDialog) {
            }
        });
        okDialog.show();
    }
}

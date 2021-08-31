package com.advantech.apphub.databridge.service;

import com.advantech.apphub.databridge.constants.ReportData;
import com.advantech.apphub.databridge.client.IDataBridgeClient;

interface IDataBridgeService {
    int toggleDebugLog(in boolean enable);

    int reportDataSync(in ReportData data);
    int reportDataAsync(in ReportData data);

    int registerDataBridgeClient(String pkgName, IDataBridgeClient dataBridgeClient);
    int unregisterDataBridgeClient(String pkgName);
}

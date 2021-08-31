package com.advantech.apphub.databridge.client;

import com.advantech.apphub.databridge.constants.ParameterData;
import com.advantech.apphub.databridge.constants.ResponseData;


interface IDataBridgeClient {
     ResponseData setData(in ParameterData param);
     ResponseData getData(in ParameterData param);
}

/*
 * Zed Attack Proxy (ZAP) and its related class files.
 * 
 * ZAP is an HTTP/HTTPS proxy for assessing web application security.
 * 
 * Copyright 2010 The ZAP Development team
 * Copyright 2014 Jay Ball - Aspect Security
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0 
 *   
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */
package org.zaproxy.zap.extension.globalexcludeurl;

import org.apache.log4j.Logger;
import org.parosproxy.paros.Constant;
import org.parosproxy.paros.extension.ExtensionAdaptor;
import org.parosproxy.paros.extension.ExtensionHook;
import org.parosproxy.paros.model.Model;
import org.zaproxy.zap.extension.api.API;

/** TODO The GlobalExcludeURL functionality is currently alpha and subject to change.  */
public class ExtensionGlobalExcludeURL extends ExtensionAdaptor  {

	public static final String NAME = "ExtensionGlobalExcludeURL"; 
	public static final String TAG = "GlobalExcludeURL"; 
		
	private OptionsGlobalExcludeURLPanel optionsGlobalExcludeURLPanel = null;
    // TODO Implement later ... private PopupMenuGenerateForm popupMenuGenerateForm = null;

	private static Logger log = Logger.getLogger(ExtensionGlobalExcludeURL.class);

	public ExtensionGlobalExcludeURL() {
		super();
		initialize();
	}

	private void initialize() {
        this.setName(NAME);
        this.setOrder(969);		// TODO find optimal load order at some point
	}

	@Override
	public void hook(ExtensionHook extensionHook) {
	    super.hook(extensionHook);

	    if (getView() != null) {
	        extensionHook.getHookView().addOptionPanel(getOptionsGlobalExcludeURLPanel());
	        // extensionHook.getHookMenu().addPopupMenuItem(this.getPopupMenuGenerateForm());
	    }

	    GlobalExcludeURLAPI api = new GlobalExcludeURLAPI(this);
        api.addApiOptions(getParam());
        API.getInstance().registerApiImplementor(api);

	}
	
    /** TODO Implement the "right click, add to GEURL list" function. */
    /*
	private PopupMenuGenerateForm getPopupMenuGenerateForm() {
		if (popupMenuGenerateForm == null) {
			this.popupMenuGenerateForm = new PopupMenuGenerateForm(Constant.messages.getString("globalexcludeurl.genForm.popup")); // FIXME lang todo
		}
		return popupMenuGenerateForm;
	}
    */
	
	private OptionsGlobalExcludeURLPanel getOptionsGlobalExcludeURLPanel() {
		if (optionsGlobalExcludeURLPanel == null) {
			optionsGlobalExcludeURLPanel = new OptionsGlobalExcludeURLPanel();
		}
		return optionsGlobalExcludeURLPanel;
	}
	
	protected GlobalExcludeURLParam getParam() {
        return Model.getSingleton().getOptionsParam().getGlobalExcludeURLParam();
	}

	@Override
    public String getAuthor() {                                                 
        return Constant.ZAP_TEAM;
    }

	
}
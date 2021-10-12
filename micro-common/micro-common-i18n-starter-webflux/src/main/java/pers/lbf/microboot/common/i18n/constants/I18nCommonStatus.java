package pers.lbf.microboot.common.i18n.constants;

import pers.lbf.microboot.common.core.status.IStatus;
import pers.lbf.microboot.common.i18n.constants.status.I18nStatus;

/**
 * @author 赖柄沣
 * @since 2021/10/12 13:03
 */
public enum I18nCommonStatus implements I18nStatus, IStatus {
    /**
     * 没有令牌
     */
    NO_TOKEN("ac001");
    private final String status;

    I18nCommonStatus(String status) {
        this.status = status;
    }

    /**
     * 状态编码
     *
     * @return code
     */
    @Override
    public String getCode() {
        return null;
    }
}

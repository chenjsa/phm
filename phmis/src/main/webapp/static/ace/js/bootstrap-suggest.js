/**
 * Bootstrap Search Suggest
 * @desc    杩欐槸涓€涓熀浜� bootstrap 鎸夐挳寮忎笅鎷夎彍鍗曠粍浠剁殑鎼滅储寤鸿鎻掍欢锛屽繀椤讳娇鐢ㄤ簬鎸夐挳寮忎笅鎷夎彍鍗曠粍浠朵笂銆�
 * @author  renxia <lzwy0820#qq.com>
 * @github  https://github.com/lzwme/bootstrap-suggest-plugin.git
 * @since   2014-10-09
 *===============================================================================
 * (c) Copyright 2014-2016 http://lzw.me All Rights Reserved.
 ********************************************************************************/
(function (factory) {
    if (typeof define === "function" && define.amd) {
        define(['jquery'], factory);
    } else if (typeof exports === 'object' && typeof module === 'object') {
        factory(require('jquery'));
    } else if (window.jQuery) {
        factory(window.jQuery);
    } else {
        throw new Error('Not found jQuery.');
    }
})(function($) {
    var VERSION = '0.1.20';
    var $window = $(window);
    var isIe = 'ActiveXObject' in window; // 鐢ㄤ簬瀵� IE 鐨勫吋瀹瑰垽鏂�
    var inputLock; // 鐢ㄤ簬涓枃杈撳叆娉曡緭鍏ユ椂閿佸畾鎼滅储

    // ie 涓嬪拰 chrome 51 浠ヤ笂娴忚鍣ㄧ増鏈紝鍑虹幇婊氬姩鏉℃椂涓嶈绠� padding
    var chromeVer = navigator.userAgent.match(/Chrome\/(\d+)/);
    if (chromeVer) {
        chromeVer = +chromeVer[1];
    }
    var notNeedCalcPadding = isIe || chromeVer > 51;

    // 涓€浜涘父閲�
    var BSSUGGEST = 'bsSuggest';
    var onDataRequestSuccess = 'onDataRequestSuccess';
    var DISABLED = 'disabled';
    var TRUE = true;
    var FALSE = false;

    /**
     * 閿欒澶勭悊
     */
    function handleError(e1, e2) {
        if (!window.console || !window.console.trace) {
            return;
        }
        console.trace(e1);
        if (e2) {
            console.trace(e2);
        }
    }
    /**
     * 鑾峰彇褰撳墠 tr 鍒楃殑鍏抽敭瀛楁暟鎹�
     */
    function getPointKeyword($list) {
        return $list.data();
    }
    /**
     * 璁剧疆鎴栬幏鍙栬緭鍏ユ鐨� alt 鍊�
     */
    function setOrGetAlt($input, val) {
        return val !== undefined ? $input.attr('alt', val) : $input.attr('alt');
    }
    /**
     * 璁剧疆鎴栬幏鍙栬緭鍏ユ鐨� data-id 鍊�
     */
    function setOrGetDataId($input, val) {
        return val !== undefined ? $input.attr('data-id', val) : $input.attr('data-id');
    }
    /**
     * 璁剧疆閫変腑鐨勫€�
     */
    function setValue($input, keywords, options) {
        if (!keywords || !keywords.key) {
            return;
        }

        var separator = options.separator || ',',
            inputValList,
            inputIdList,
            dataId = setOrGetDataId($input);

        if (options && options.multiWord) {
            inputValList = $input.val().split(separator);
            inputValList[inputValList.length - 1] = keywords.key;

            //澶氬叧閿瓧妫€绱㈡敮鎸佽缃甶d --- 瀛樺湪 bug锛屼笉寤鸿浣跨敤
            if (!dataId) {
                inputIdList = [keywords.id];
            } else {
                inputIdList = dataId.split(separator);
                inputIdList.push(keywords.id);
            }

            setOrGetDataId($input, inputIdList.join(separator))
                .val(inputValList.join(separator))
                .focus();
        } else {
            setOrGetDataId($input, keywords.id).val(keywords.key).focus();
        }

        $input.trigger('onSetSelectValue', [keywords, (options.data.value || options._lastData.value)[keywords.index]]);
    }
    /**
     * 璋冩暣閫夋嫨鑿滃崟浣嶇疆
     * @param {Object} $input
     * @param {Object} $dropdownMenu
     * @param {Object} options
     */
    function adjustDropMenuPos($input, $dropdownMenu, options) {
        if (!$dropdownMenu.is(':visible')) {
            return;
        }

        var $parent = $input.parent();
        var parentHeight = $parent.height();
        var parentWidth = $parent.width();

        if (options.autoDropup) {
            setTimeout(function() {
                var offsetTop = $input.offset().top;
                var winScrollTop = $window.scrollTop();
                var menuHeight = $dropdownMenu.height();

                if ( // 鑷姩鍒ゆ柇鑿滃崟鍚戜笂灞曞紑
                    ($window.height() + winScrollTop - offsetTop) < menuHeight && // 鍋囧鍚戜笅浼氭拺闀块〉闈�
                    offsetTop > (menuHeight + winScrollTop) // 鑰屼笖鍚戜笂涓嶄細鎾戝埌椤堕儴
                ) {
                    $parent.addClass('dropup');
                } else {
                    $parent.removeClass('dropup');
                }
            }, 10);
        }

        // 鍒楄〃瀵归綈鏂瑰紡
        var dmcss = {};
        if (options.listAlign === 'left') {
            dmcss = {
                'left': $input.siblings('div').width() - parentWidth,
                'right': 'auto'
            };
        } else if (options.listAlign === 'right') {
            dmcss = {
                'left': 'auto',
                'right': 0
            };
        }

        // ie 涓嬶紝涓嶆樉绀烘寜閽椂鐨� top/bottom
        if (isIe && !options.showBtn) {
            if (!$parent.hasClass('dropup')) {
                dmcss.top = parentHeight;
                dmcss.bottom = 'auto';
            } else {
                dmcss.top = 'auto';
                dmcss.bottom = parentHeight;
            }
        }

        // 鏄惁鑷姩鏈€灏忓搴�
        if (!options.autoMinWidth) {
            dmcss.minWidth = parentWidth;
        }
        /* else {
            dmcss['width'] = 'auto';
        }*/

        $dropdownMenu.css(dmcss);

        return $input;
    }
    /**
     * 璁剧疆杈撳叆妗嗚儗鏅壊
     * 褰撹缃簡 indexId锛岃€岃緭鍏ユ鐨� data-id 涓虹┖鏃讹紝杈撳叆妗嗗姞杞借鍛婅壊
     */
    function setBackground($input, options) {
        var inputbg, bg, warnbg;

        if ((options.indexId === -1 && !options.idField) || options.multiWord) {
            return $input;
        }

        inputbg = $input.css('backgroundColor').replace(/ /g, '').split(',', 3).join(',');
        // console.log(inputbg);
        bg = options.inputBgColor || 'rgba(255,255,255,0.1)';
        warnbg = options.inputWarnColor || 'rgba(255,255,0,0.1)';
        if (setOrGetDataId($input) || !$input.val()) {
            return $input.css('background', bg);
        }

        // 鑷敱杈撳叆鐨勫唴瀹癸紝璁剧疆鑳屾櫙鑹�
        if (!~warnbg.indexOf(inputbg)) {
            $input.trigger('onUnsetSelectValue') // 瑙﹀彂鍙栨秷data-id浜嬩欢
                .css('background', warnbg);
        }

        return $input;
    }
    /**
     * 璋冩暣婊戝姩鏉�
     */
    function adjustScroll($input, $dropdownMenu, options) {
        // 鎺у埗婊戝姩鏉�
        var $hover = $input.parent().find('tbody tr.' + options.listHoverCSS),
            pos, maxHeight;

        if ($hover.length) {
            pos = ($hover.index() + 3) * $hover.height();
            maxHeight = +$dropdownMenu.css('maxHeight').replace('px', '');

            if (pos > maxHeight || $dropdownMenu.scrollTop() > maxHeight) {
                pos = pos - maxHeight;
            } else {
                pos = 0;
            }

            $dropdownMenu.scrollTop(pos);
        }
    }
    /**
     * 瑙ｉ櫎鎵€鏈夊垪琛� hover 鏍峰紡
     */
    function unHoverAll($dropdownMenu, options) {
        $dropdownMenu.find('tr.' + options.listHoverCSS).removeClass(options.listHoverCSS);
    }
    /**
     * 楠岃瘉 $input 瀵硅薄鏄惁绗﹀悎鏉′欢
     *   1. 蹇呴』涓� bootstrap 涓嬫媺寮忚彍鍗�
     *   2. 蹇呴』鏈垵濮嬪寲杩�
     */
    function checkInput($input, $dropdownMenu, options) {
        if (
            !$dropdownMenu.length || // 杩囨护闈� bootstrap 涓嬫媺寮忚彍鍗曞璞�
            $input.data(BSSUGGEST) // 鏄惁宸茬粡鍒濆鍖栫殑妫€娴�
        ) {
            return FALSE;
        }

        $input.data(BSSUGGEST, {
            options: options
        });

        return TRUE;
    }
    /**
     * 鏁版嵁鏍煎紡妫€娴�
     * 妫€娴� ajax 杩斿洖鎴愬姛鏁版嵁鎴� data 鍙傛暟鏁版嵁鏄惁鏈夋晥
     * data 鏍煎紡锛歿"value": [{}, {}...]}
     */
    function checkData(data) {
        var isEmpty = TRUE, o;

        for (o in data) {
            if (o === 'value') {
                isEmpty = FALSE;
                break;
            }
        }
        if (isEmpty) {
            handleError('杩斿洖鏁版嵁鏍煎紡閿欒!');
            return FALSE;
        }
        if (!data.value.length) {
            // handleError('杩斿洖鏁版嵁涓虹┖!');
            return FALSE;
        }

        return data;
    }
    /**
     * 鍒ゆ柇瀛楁鍚嶆槸鍚﹀湪 options.effectiveFields 閰嶇疆椤逛腑
     * @param  {String} field   瑕佸垽鏂殑瀛楁鍚�
     * @param  {Object} options
     * @return {Boolean}        effectiveFields 涓虹┖鏃跺缁堣繑鍥� true
     */
    function inEffectiveFields(field, options) {
        var effectiveFields = options.effectiveFields;

        return !(field === '__index' ||
            effectiveFields.length &&
            !~$.inArray(field, effectiveFields));
    }
    /**
     * 鍒ゆ柇瀛楁鍚嶆槸鍚﹀湪 options.searchFields 鎼滅储瀛楁閰嶇疆涓�
     */
    function inSearchFields(field, options) {
        return ~$.inArray(field, options.searchFields);
    }
    /**
     * 閫氳繃涓嬫媺鑿滃崟鏄剧ず鎻愮ず鏂囨
     */
    function showTip(tip, $input, $dropdownMenu, options) {
        $dropdownMenu.html('<div style="padding:10px 5px 5px">' + tip + '</div>').show();
        adjustDropMenuPos($input, $dropdownMenu, options);
    }
    /**
     * 涓嬫媺鍒楄〃鍒锋柊
     * 浣滀负 fnGetData 鐨� callback 鍑芥暟璋冪敤
     */
    function refreshDropMenu($input, data, options) {
        var $dropdownMenu = $input.parent().find('ul:eq(0)'),
            len, i, field, index = 0,
            tds,
            html = ['<table class="table table-condensed table-sm" style="margin:0">'],
            idValue, keyValue; // 浣滀负杈撳叆妗� data-id 鍜屽唴瀹圭殑瀛楁鍊�
        var dataList = data.value;

        if (!data || !(len = dataList.length)) {
            if (options.emptyTip) {
                showTip(options.emptyTip, $input, $dropdownMenu, options);
            } else {
                $dropdownMenu.empty().hide();
            }
            return $input;
        }

        // 鐩稿悓鏁版嵁锛屼笉鐢ㄧ户缁覆鏌撲簡
        if (
            options._lastData &&
            JSON.stringify(options._lastData) === JSON.stringify(data) &&
            $dropdownMenu.find('tr').length === len
        ) {
            $dropdownMenu.show();
            return adjustDropMenuPos($input, $dropdownMenu, options);
            // return $input;
        }
        options._lastData = data;

        // 鐢熸垚琛ㄥご
        if (options.showHeader) {
            html.push('<thead><tr>');
            for (field in dataList[0]) {
                if (!inEffectiveFields(field, options)) {
                    continue;
                }

                html.push('<th>', (options.effectiveFieldsAlias[field] || field),
                    index === 0 ? ('(' + len + ')') : '' , // 琛ㄥご绗竴鍒楄褰曟€绘暟
                    '</th>');

                index++;
            }
            html.push('</tr></thead>');
        }
        html.push('<tbody>');

        // console.log(data, len);
        // 鎸夊垪鍔犳暟鎹�
        var dataI;
        for (i = 0; i < len; i++) {
            index = 0;
            tds = [];
            dataI = dataList[i];
            idValue = dataI[options.idField] || '';
            keyValue = dataI[options.keyField] || '';

            for (field in dataI) {
                // 鏍囪浣滀负 value 鍜� 浣滀负 id 鐨勫€�
                if (!keyValue && options.indexKey === index) {
                    keyValue = dataI[field];
                }
                if (!idValue && options.indexId === index) {
                    idValue = dataI[field];
                }

                index++;

                // 鍒楄〃涓彧鏄剧ず鏈夋晥鐨勫瓧娈�
                if (inEffectiveFields(field, options)) {
                    tds.push('<td data-name="', field, '">', dataI[field], '</td>');
                }
            }

            html.push('<tr data-index="', (dataI.__index || i),
                '" data-id="', idValue,
                '" data-key="', keyValue, '">',
                tds.join(''), '</tr>');
        }
        html.push('</tbody></table>');

        $dropdownMenu.html(html.join('')).show();

        // scrollbar 瀛樺湪鏃讹紝寤舵椂鍒板姩鐢荤粨鏉熸椂璋冩暣 padding
        setTimeout(function() {
            if (notNeedCalcPadding) {
                return;
            }

            var $table = $dropdownMenu.find('table:eq(0)'),
                pdr = 0,
                mgb = 0;

            if (
                $dropdownMenu.height() < $table.height() &&
                +$dropdownMenu.css('minWidth').replace('px', '') < $dropdownMenu.width()
            ) {
                pdr = 18;
                mgb = 20;
            }

            $dropdownMenu.css('paddingRight', pdr);
            $table.css('marginBottom', mgb);
        }, 301);

        adjustDropMenuPos($input, $dropdownMenu, options);

        return $input;
    }
    /**
     * ajax 鑾峰彇鏁版嵁
     * @param  {Object} options
     * @return {Object}         $.Deferred
     */
    function ajax(options, keyword) {
        keyword = keyword || '';

        var preAjax = options._preAjax;

        if (preAjax && preAjax.abort && preAjax.readyState !== 4) {
            // console.log('abort pre ajax');
            preAjax.abort();
        }

        var ajaxParam = {
            type: 'GET',
            dataType: options.jsonp ? 'jsonp' : 'json',
            timeout: 5000,
        };

        // jsonp
        if (options.jsonp) {
            ajaxParam.jsonp = options.jsonp;
        }

        // 鑷畾涔� ajax 璇锋眰鍙傛暟鐢熸垚鏂规硶
        var adjustAjaxParam,
            fnAdjustAjaxParam = options.fnAdjustAjaxParam;

        if ($.isFunction(fnAdjustAjaxParam)) {
            adjustAjaxParam = fnAdjustAjaxParam(keyword, options);

            // options.fnAdjustAjaxParam 杩斿洖false锛屽垯缁堟 ajax 璇锋眰
            if (FALSE === adjustAjaxParam) {
                return;
            }

            $.extend(ajaxParam, adjustAjaxParam);
        }

        // url 璋冩暣
        ajaxParam.url = function() {
            if (!keyword || ajaxParam.data) {
                return ajaxParam.url || options.url;
            }

            var type = '?';
            if (/=$/.test(options.url)) {
                type = '';
            } else if (/\?/.test(options.url)) {
                type = '&';
            }

            return options.url + type + keyword;
        }();

        return options._preAjax = $.ajax(ajaxParam).done(function(result) {
            options.data = options.fnProcessData(result);
        }).fail(function(err) {
            if (options.fnAjaxFail) {
                options.fnAjaxFail(err, options);
            }
        });
    }
    /**
     * 妫€娴� keyword 涓� value 鏄惁瀛樺湪浜掔浉鍖呭惈
     * @param  {String}  keyword 鐢ㄦ埛杈撳叆鐨勫叧閿瓧
     * @param  {String}  key     鍖归厤瀛楁鐨� key
     * @param  {String}  value   key 瀛楁瀵瑰簲鐨勫€�
     * @param  {Object}  options
     * @return {Boolean}         鍖呭惈/涓嶅寘鍚�
     */
    function isInWord(keyword, key, value, options) {
        value = $.trim(value);

        if (options.ignorecase) {
            keyword = keyword.toLocaleLowerCase();
            value = value.toLocaleLowerCase();
        }

        return value &&
            (inEffectiveFields(key, options) || inSearchFields(key, options)) && // 蹇呴』鍦ㄦ湁鏁堢殑鎼滅储瀛楁涓�
            (
                ~value.indexOf(keyword) || // 鍖归厤鍊煎寘鍚叧閿瓧
                options.twoWayMatch && ~keyword.indexOf(value) // 鍏抽敭瀛楀寘鍚尮閰嶅€�
            );
    }
    /**
     * 閫氳繃 ajax 鎴� json 鍙傛暟鑾峰彇鏁版嵁
     */
    function getData(keyword, $input, callback, options) {
        var data, validData, filterData = {
                value: []
            },
            i, key, len,
            fnPreprocessKeyword = options.fnPreprocessKeyword;

        keyword = keyword || '';
        // 鑾峰彇鏁版嵁鍓嶅鍏抽敭瀛楅澶勭悊鏂规硶
        if ($.isFunction(fnPreprocessKeyword)) {
            keyword = fnPreprocessKeyword(keyword, options);
        }

        // 缁欎簡url鍙傛暟锛屽垯浠庢湇鍔″櫒 ajax 璇锋眰
        // console.log(options.url + keyword);
        if (options.url) {
            var timer;
            if (options.searchingTip) {
                timer = setTimeout(function() {
                    showTip(options.searchingTip, $input, $input.parent().find('ul'), options);
                }, 600);
            }

            ajax(options, keyword).done(function(result) {
                callback($input, options.data, options); // 涓� refreshDropMenu
                $input.trigger(onDataRequestSuccess, result);
                if (options.getDataMethod === 'firstByUrl') {
                    options.url = null;
                }
            }).always(function() {
                timer && clearTimeout(timer);
            });
        } else {
            // 娌℃湁缁欏嚭 url 鍙傛暟锛屽垯浠� data 鍙傛暟鑾峰彇
            data = options.data;
            validData = checkData(data);
            // 鏈湴鐨� data 鏁版嵁锛屽垯鍦ㄦ湰鍦拌繃婊�
            if (validData) {
                if (keyword) {
                    // 杈撳叆涓嶄负绌烘椂鍒欒繘琛屽尮閰�
                    len = data.value.length;
                    for (i = 0; i < len; i++) {
                        for (key in data.value[i]) {
                            if (
                                data.value[i][key] &&
                                isInWord(keyword, key, data.value[i][key] + '', options)
                            ) {
                                filterData.value.push(data.value[i]);
                                filterData.value[filterData.value.length - 1].__index = i;
                                break;
                            }
                        }
                    }
                } else {
                    filterData = data;
                }
            }

            callback($input, filterData, options);
        } // else
    }
    /**
     * 鏁版嵁澶勭悊
     * url 鑾峰彇鏁版嵁鏃讹紝瀵规暟鎹殑澶勭悊锛屼綔涓� fnGetData 涔嬪悗鐨勫洖璋冨鐞�
     */
    function processData(data) {
        return checkData(data);
    }
    /**
     * 鍙栧緱 clearable 娓呴櫎鎸夐挳
     */
    function getIClear($input, options) {
        var $iClear = $input.prev('i.clearable');

        // 鏄惁鍙竻闄ゅ凡杈撳叆鐨勫唴瀹�(娣诲姞娓呴櫎鎸夐挳)
        if (options.clearable && !$iClear.length) {
                $iClear = $('<i class="clearable glyphicon glyphicon-remove"></i>')
                    .prependTo($input.parent());
        }

        return $iClear.css({
            position: 'absolute',
            top: 12,
            right: options.showBtn ? ($input.next('.input-group-btn').width() || 33) + 2 : 12,
            zIndex: 4,
            cursor: 'pointer',
            fontSize: 12
        }).hide();
    }
    /**
     * 榛樿鐨勯厤缃€夐」
     * @type {Object}
     */
    var defaultOptions = {
        url: null,                      // 璇锋眰鏁版嵁鐨� URL 鍦板潃
        jsonp: null,                    // 璁剧疆姝ゅ弬鏁板悕锛屽皢寮€鍚痡sonp鍔熻兘锛屽惁鍒欎娇鐢╦son鏁版嵁缁撴瀯
        data: {
            value: []
        },                              // 鎻愮ず鎵€鐢ㄧ殑鏁版嵁锛屾敞鎰忔牸寮�
        indexId: 0,                     // 姣忕粍鏁版嵁鐨勭鍑犱釜鏁版嵁锛屼綔涓篿nput杈撳叆妗嗙殑 data-id锛岃涓� -1 涓� idField 涓虹┖鍒欎笉璁剧疆姝ゅ€�
        indexKey: 0,                    // 姣忕粍鏁版嵁鐨勭鍑犱釜鏁版嵁锛屼綔涓篿nput杈撳叆妗嗙殑鍐呭
        idField: '',                    // 姣忕粍鏁版嵁鐨勫摢涓瓧娈典綔涓� data-id锛屼紭鍏堢骇楂樹簬 indexId 璁剧疆锛堟帹鑽愶級
        keyField: '',                   // 姣忕粍鏁版嵁鐨勫摢涓瓧娈典綔涓鸿緭鍏ユ鍐呭锛屼紭鍏堢骇楂樹簬 indexKey 璁剧疆锛堟帹鑽愶級

        /* 鎼滅储鐩稿叧 */
        autoSelect: TRUE,               // 閿洏鍚戜笂/涓嬫柟鍚戦敭鏃讹紝鏄惁鑷姩閫夋嫨鍊�
        allowNoKeyword: TRUE,           // 鏄惁鍏佽鏃犲叧閿瓧鏃惰姹傛暟鎹�
        getDataMethod: 'firstByUrl',    // 鑾峰彇鏁版嵁鐨勬柟寮忥紝url锛氫竴鐩翠粠url璇锋眰锛沝ata锛氫粠 options.data 鑾峰彇锛沠irstByUrl锛氱涓€娆′粠Url鑾峰彇鍏ㄩ儴鏁版嵁锛屼箣鍚庝粠options.data鑾峰彇
        delayUntilKeyup: FALSE,         // 鑾峰彇鏁版嵁鐨勬柟寮� 涓� firstByUrl 鏃讹紝鏄惁寤惰繜鍒版湁杈撳叆鏃舵墠璇锋眰鏁版嵁
        ignorecase: FALSE,              // 鍓嶇鎼滅储鍖归厤鏃讹紝鏄惁蹇界暐澶у皬鍐�
        effectiveFields: [],            // 鏈夋晥鏄剧ず浜庡垪琛ㄤ腑鐨勫瓧娈碉紝闈炴湁鏁堝瓧娈甸兘浼氳繃婊わ紝榛樿鍏ㄩ儴鏈夋晥銆�
        effectiveFieldsAlias: {},       // 鏈夋晥瀛楁鐨勫埆鍚嶅璞★紝鐢ㄤ簬 header 鐨勬樉绀�
        searchFields: [],               // 鏈夋晥鎼滅储瀛楁锛屼粠鍓嶇鎼滅储杩囨护鏁版嵁鏃朵娇鐢紝浣嗕笉涓€瀹氭樉绀哄湪鍒楄〃涓€俥ffectiveFields 閰嶇疆瀛楁涔熶細鐢ㄤ簬鎼滅储杩囨护
        twoWayMatch: TRUE,              // 鏄惁鍙屽悜鍖归厤鎼滅储銆備负 true 鍗宠緭鍏ュ叧閿瓧鍖呭惈鎴栧寘鍚簬鍖归厤瀛楁鍧囪涓哄尮閰嶆垚鍔燂紝涓� false 鍒欒緭鍏ュ叧閿瓧鍖呭惈浜庡尮閰嶅瓧娈佃涓哄尮閰嶆垚鍔�
        multiWord: FALSE,               // 浠ュ垎闅旂鍙峰垎鍓茬殑澶氬叧閿瓧鏀寔
        separator: ',',                 // 澶氬叧閿瓧鏀寔鏃剁殑鍒嗛殧绗︼紝榛樿涓哄崐瑙掗€楀彿
        delay: 300,                     // 鎼滅储瑙﹀彂鐨勫欢鏃舵椂闂撮棿闅旓紝鍗曚綅姣
        emptyTip: '',                   // 鏌ヨ涓虹┖鏃舵樉绀虹殑鍐呭锛屽彲涓� html
        searchingTip: '鎼滅储涓�...',      // ajax 鎼滅储鏃舵樉绀虹殑鎻愮ず鍐呭锛屽綋鎼滅储鏃堕棿杈冮暱鏃剁粰鍑烘鍦ㄦ悳绱㈢殑鎻愮ず

        /* UI */
        autoDropup: FALSE,              // 閫夋嫨鑿滃崟鏄惁鑷姩鍒ゆ柇鍚戜笂灞曞紑銆傝涓� true锛屽垯褰撲笅鎷夎彍鍗曢珮搴﹁秴杩囩獥浣擄紝涓斿悜涓婃柟鍚戜笉浼氳绐椾綋瑕嗙洊锛屽垯閫夋嫨鑿滃崟鍚戜笂寮瑰嚭
        autoMinWidth: FALSE,            // 鏄惁鑷姩鏈€灏忓搴︼紝璁句负 false 鍒欐渶灏忓搴︿笉灏忎簬杈撳叆妗嗗搴�
        showHeader: FALSE,              // 鏄惁鏄剧ず閫夋嫨鍒楄〃鐨� header銆備负 true 鏃讹紝鏈夋晥瀛楁澶т簬涓€鍒楀垯鏄剧ず琛ㄥご
        showBtn: TRUE,                  // 鏄惁鏄剧ず涓嬫媺鎸夐挳
        inputBgColor: '',               // 杈撳叆妗嗚儗鏅壊锛屽綋涓庡鍣ㄨ儗鏅壊涓嶅悓鏃讹紝鍙兘闇€瑕佽椤圭殑閰嶇疆
        inputWarnColor: 'rgba(255,0,0,.1)', // 杈撳叆妗嗗唴瀹逛笉鏄笅鎷夊垪琛ㄩ€夋嫨鏃剁殑璀﹀憡鑹�
        listStyle: {
            'padding-top': 0,
            'max-height': '375px',
            'max-width': '800px',
            'overflow': 'auto',
            'width': 'auto',
            'transition': '0.3s',
            '-webkit-transition': '0.3s',
            '-moz-transition': '0.3s',
            '-o-transition': '0.3s'
        },                              // 鍒楄〃鐨勬牱寮忔帶鍒�
        listAlign: 'left',              // 鎻愮ず鍒楄〃瀵归綈浣嶇疆锛宭eft/right/auto
        listHoverStyle: 'background: #07d; color:#fff', // 鎻愮ず妗嗗垪琛ㄩ紶鏍囨偓娴殑鏍峰紡
        listHoverCSS: 'jhover',         // 鎻愮ず妗嗗垪琛ㄩ紶鏍囨偓娴殑鏍峰紡鍚嶇О
        clearable: FALSE,               // 鏄惁鍙竻闄ゅ凡杈撳叆鐨勫唴瀹�

        /* key */
        keyLeft: 37,                    // 鍚戝乏鏂瑰悜閿紝涓嶅悓鐨勬搷浣滅郴缁熷彲鑳戒細鏈夊樊鍒紝鍒欒嚜琛屽畾涔�
        keyUp: 38,                      // 鍚戜笂鏂瑰悜閿�
        keyRight: 39,                   // 鍚戝彸鏂瑰悜閿�
        keyDown: 40,                    // 鍚戜笅鏂瑰悜閿�
        keyEnter: 13,                   // 鍥炶溅閿�

        /* methods */
        fnProcessData: processData,     // 鏍煎紡鍖栨暟鎹殑鏂规硶锛岃繑鍥炴暟鎹牸寮忓弬鑰� data 鍙傛暟
        fnGetData: getData,             // 鑾峰彇鏁版嵁鐨勬柟娉曪紝鏃犵壒娈婇渶姹備竴鑸笉浣滆缃�
        fnAdjustAjaxParam: null,        // 璋冩暣 ajax 璇锋眰鍙傛暟鏂规硶锛岀敤浜庢洿澶氱殑璇锋眰閰嶇疆闇€姹傘€傚瀵硅姹傚叧閿瓧浣滆繘涓€姝ュ鐞嗐€佷慨鏀硅秴鏃舵椂闂寸瓑
        fnPreprocessKeyword: null,      // 鎼滅储杩囨护鏁版嵁鍓嶏紝瀵硅緭鍏ュ叧閿瓧浣滆繘涓€姝ュ鐞嗘柟娉曘€傛敞鎰忥紝搴旇繑鍥炲瓧绗︿覆
        fnAjaxFail: null,               // ajax 澶辫触鏃跺洖璋冩柟娉�
    };

    var methods = {
        init: function(options) {
            // 鍙傛暟璁剧疆
            var self = this;
            options = options || {};

            // 榛樿閰嶇疆鏈夋晥鏄剧ず瀛楁澶氫簬涓€涓紝鍒欐樉绀哄垪琛ㄨ〃澶达紝鍚﹀垯涓嶆樉绀�
            if (undefined === options.showHeader && options.effectiveFields && options.effectiveFields.length > 1) {
                options.showHeader = TRUE;
            }

            options = $.extend(TRUE, {}, defaultOptions, options);

            // 鏃х殑鏂规硶鍏煎
            if (options.processData) {
                options.fnProcessData = options.processData;
            }

            if (options.getData) {
                options.fnGetData = options.getData;
            }

            if (options.getDataMethod === 'firstByUrl' && options.url && !options.delayUntilKeyup) {
                ajax(options).done(function(result) {
                    options.url = null;
                    self.trigger(onDataRequestSuccess, result);
                });
            }

            // 榧犳爣婊戝姩鍒版潯鐩牱寮�
            if (!$('#' + BSSUGGEST).length) {
                $('head:eq(0)').append('<style id="' + BSSUGGEST + '">.' + options.listHoverCSS + '{' + options.listHoverStyle + '}</style>');
            }

            return self.each(function() {
                var $input = $(this),
                    $parent = $input.parent(),
                    $iClear = getIClear($input, options),
                    isMouseenterMenu,
                    keyupTimer, // keyup 涓� input 浜嬩欢寤舵椂瀹氭椂鍣�
                    $dropdownMenu = $parent.find('ul:eq(0)');

                // 楠岃瘉杈撳叆妗嗗璞℃槸鍚︾鍚堟潯浠�
                if (!checkInput($input, $dropdownMenu, options)) {
                    console.warn('涓嶆槸涓€涓爣鍑嗙殑 bootstrap 涓嬫媺寮忚彍鍗曟垨宸插垵濮嬪寲:', $input);
                    return;
                }

                // 鏄惁鏄剧ず button 鎸夐挳
                if (!options.showBtn) {
                    $input.css('borderRadius', 4);
                    $parent.css('width', '100%')
                        .find('.btn:eq(0)').hide();
                }

                // 绉婚櫎 disabled 绫伙紝骞剁鐢ㄨ嚜鍔ㄥ畬鎴�
                $input.removeClass(DISABLED).prop(DISABLED, FALSE).attr('autocomplete', 'off');
                // dropdown-menu 澧炲姞淇グ
                $dropdownMenu.css(options.listStyle);

                // 榛樿鑳屾櫙鑹�
                if (!options.inputBgColor) {
                    options.inputBgColor = $input.css('backgroundColor');
                }

                // 寮€濮嬩簨浠跺鐞�
                $input.on('keydown', function(event) {
                    var currentList, tipsKeyword; // 鎻愮ず鍒楄〃涓婅閫変腑鐨勫叧閿瓧

                    // setOrGetDataId($input, '');

                    // 褰撴彁绀哄眰鏄剧ず鏃舵墠瀵归敭鐩樹簨浠跺鐞�
                    if (!$dropdownMenu.is(':visible')) {
                        return;
                    }

                    currentList = $dropdownMenu.find('.' + options.listHoverCSS);
                    tipsKeyword = ''; // 鎻愮ず鍒楄〃涓婅閫変腑鐨勫叧閿瓧

                    unHoverAll($dropdownMenu, options);

                    if (event.keyCode === options.keyDown) { // 濡傛灉鎸夌殑鏄悜涓嬫柟鍚戦敭
                        if (!currentList.length) {
                            // 濡傛灉鎻愮ず鍒楄〃娌℃湁涓€涓閫変腑,鍒欏皢鍒楄〃绗竴涓€変腑
                            tipsKeyword = getPointKeyword($dropdownMenu.find('tbody tr:first').mouseover());
                        } else if (!currentList.next().length) {
                            // 濡傛灉鏄渶鍚庝竴涓閫変腑,鍒欏彇娑堥€変腑,鍗冲彲璁や负鏄緭鍏ユ琚€変腑锛屽苟鎭㈠杈撳叆鐨勫€�
                            if (options.autoSelect) {
                                setOrGetDataId($input, '').val(setOrGetAlt($input));
                            }
                        } else {
                            // 閫変腑涓嬩竴琛�
                            tipsKeyword = getPointKeyword(currentList.next().mouseover());
                        }
                        // 鎺у埗婊戝姩鏉�
                        adjustScroll($input, $dropdownMenu, options);

                        if (!options.autoSelect) {
                            return;
                        }
                    } else if (event.keyCode === options.keyUp) { // 濡傛灉鎸夌殑鏄悜涓婃柟鍚戦敭
                        if (!currentList.length) {
                            tipsKeyword = getPointKeyword($dropdownMenu.find('tbody tr:last').mouseover());
                        } else if (!currentList.prev().length) {
                            if (options.autoSelect) {
                                setOrGetDataId($input, '').val(setOrGetAlt($input));
                            }
                        } else {
                            // 閫変腑鍓嶄竴琛�
                            tipsKeyword = getPointKeyword(currentList.prev().mouseover());
                        }

                        // 鎺у埗婊戝姩鏉�
                        adjustScroll($input, $dropdownMenu, options);

                        if (!options.autoSelect) {
                            return;
                        }
                    } else if (event.keyCode === options.keyEnter) {
                        tipsKeyword = getPointKeyword(currentList);
                        $dropdownMenu.hide(); // .empty();
                    } else {
                        setOrGetDataId($input, '');
                    }

                    // 璁剧疆鍊� tipsKeyword
                    // console.log(tipsKeyword);
                    setValue($input, tipsKeyword, options);
                }).on('compositionstart', function(event) {
                    // 涓枃杈撳叆寮€濮嬶紝閿佸畾
                    // console.log('compositionstart');
                    inputLock = TRUE;
                }).on('compositionend', function(event) {
                    // 涓枃杈撳叆缁撴潫锛岃В闄ら攣瀹�
                    // console.log('compositionend');
                    inputLock = FALSE;
                }).on('keyup input paste', function(event) {
                    var word;

                    if (event.keyCode) {
                        setBackground($input, options);
                    }

                    // 濡傛灉寮硅捣鐨勯敭鏄洖杞︺€佸悜涓婃垨鍚戜笅鏂瑰悜閿垯杩斿洖
                    if (~$.inArray(event.keyCode, [options.keyDown, options.keyUp, options.keyEnter])) {
                        $input.val($input.val()); // 璁╅紶鏍囪緭鍏ヨ烦鍒版渶鍚�
                        return;
                    }

                    clearTimeout(keyupTimer);
                    keyupTimer = setTimeout(function() {
                        // console.log('input keyup', event);

                        // 閿佸畾鐘舵€侊紝杩斿洖
                        if (inputLock) {
                            return;
                        }

                        word = $input.val();

                        // 鑻ヨ緭鍏ユ鍊兼病鏈夋敼鍙樺垯杩斿洖
                        if ($.trim(word) && word === setOrGetAlt($input)) {
                            return;
                        }

                        // 褰撴寜涓嬮敭涔嬪墠璁板綍杈撳叆妗嗗€�,浠ユ柟渚挎煡鐪嬮敭寮硅捣鏃跺€兼湁娌℃湁鍙�
                        setOrGetAlt($input, word);

                        if (options.multiWord) {
                            word = word.split(options.separator).reverse()[0];
                        }

                        // 鏄惁鍏佽绌烘暟鎹煡璇�
                        if (!word.length && !options.allowNoKeyword) {
                            return;
                        }

                        options.fnGetData($.trim(word), $input, refreshDropMenu, options);
                    }, options.delay || 300);
                }).on('focus', function() {
                    // console.log('input focus');
                    adjustDropMenuPos($input, $dropdownMenu, options);
                }).on('blur', function() {
                    if (!isMouseenterMenu) { // 涓嶆槸杩涘叆涓嬫媺鍒楄〃鐘舵€侊紝鍒欓殣钘忓垪琛�
                        $dropdownMenu.css('display', '');
                    }
                }).on('click', function() {
                    // console.log('input click');
                    var word = $input.val();

                    if (
                        $.trim(word) &&
                        word === setOrGetAlt($input) &&
                        $dropdownMenu.find('table tr').length
                    ) {
                        return $dropdownMenu.show();
                    }

                    // if ($dropdownMenu.css('display') !== 'none') {
                    if ($dropdownMenu.is(':visible')) {
                        return;
                    }

                    if (options.multiWord) {
                        word = word.split(options.separator).reverse()[0];
                    }

                    // 鏄惁鍏佽绌烘暟鎹煡璇�
                    if (!word.length && !options.allowNoKeyword) {
                        return;
                    }

                    // console.log('word', word);
                    options.fnGetData($.trim(word), $input, refreshDropMenu, options);
                });

                // 涓嬫媺鎸夐挳鐐瑰嚮鏃�
                $parent.find('.btn:eq(0)').attr('data-toggle', '').click(function() {
                    var display = 'none';

                    // if ($dropdownMenu.is(':visible')) {
                    if ($dropdownMenu.css('display') === display) {
                        display = 'block';
                        if (options.url) {
                            $input.click().focus();
                            if (!$dropdownMenu.find('tr').length) {
                                display = 'none';
                            }
                        } else {
                            // 涓嶄互 keyword 浣滀负杩囨护锛屽睍绀烘墍鏈夌殑鏁版嵁
                            refreshDropMenu($input, options.data, options);
                        }
                    }

                    $dropdownMenu.css('display', display);
                    return FALSE;
                });

                // 鍒楄〃涓粦鍔ㄦ椂锛岃緭鍏ユ澶卞幓鐒︾偣
                $dropdownMenu.mouseenter(function() {
                        // console.log('mouseenter')
                        isMouseenterMenu = 1;
                        $input.blur();
                    }).mouseleave(function() {
                        // console.log('mouseleave')
                        isMouseenterMenu = 0;
                        $input.focus();
                    }).on('mouseenter', 'tbody tr', function() {
                        // 琛屼笂鐨勭Щ鍔ㄤ簨浠�
                        unHoverAll($dropdownMenu, options);
                        $(this).addClass(options.listHoverCSS);

                        return FALSE; // 闃绘鍐掓场
                    })
                    .on('mousedown', 'tbody tr', function() {
                        var keywords = getPointKeyword($(this));
                        setValue($input, keywords, options);
                        setOrGetAlt($input, keywords.key);
                        setBackground($input, options);
                        $dropdownMenu.hide();
                    });

                // 瀛樺湪娓呯┖鎸夐挳
                if ($iClear.length) {
                    $iClear.click(function () {
                        setOrGetDataId($input, '').val('');
                        setBackground($input, options);
                    });

                    $parent.mouseenter(function() {
                        if (!$input.prop(DISABLED)) {
                            $iClear.show();
                        }
                    }).mouseleave(function() {
                        $iClear.hide();
                    });
                }

            });
        },
        show: function() {
            return this.each(function() {
                $(this).click();
            });
        },
        hide: function() {
            return this.each(function() {
                $(this).parent().find('ul:eq(0)').css('display', '');
            });
        },
        disable: function() {
            return this.each(function() {
                $(this).attr(DISABLED, TRUE)
                    .parent().find('.btn:eq(0)').prop(DISABLED, TRUE);
            });
        },
        enable: function() {
            return this.each(function() {
                $(this).attr(DISABLED, FALSE)
                    .parent().find('.btn:eq(0)').prop(DISABLED, FALSE);
            });
        },
        destroy: function() {
            return this.each(function() {
                $(this).off().removeData(BSSUGGEST).removeAttr('style')
                    .parent().find('.btn:eq(0)').off().show().attr('data-toggle', 'dropdown').prop(DISABLED, FALSE) // .addClass(DISABLED);
                    .next().css('display', '').off();
            });
        },
        version: function() {
            return VERSION;
        }
    };

    $.fn[BSSUGGEST] = function(options) {
        // 鏂规硶鍒ゆ柇
        if (typeof options === 'string' && methods[options]) {
            var inited = TRUE;
            this.each(function() {
                if (!$(this).data(BSSUGGEST)) {
                    return inited = FALSE;
                }
            });
            // 鍙鏈変竴涓湭鍒濆鍖栵紝鍒欏叏閮ㄩ兘涓嶆墽琛屾柟娉曪紝闄ら潪鏄� init 鎴� version
            if (!inited && 'init' !== options && 'version' !== options) {
                return this;
            }

            // 濡傛灉鏄柟娉曪紝鍒欏弬鏁扮涓€涓负鍑芥暟鍚嶏紝浠庣浜屼釜寮€濮嬩负鍑芥暟鍙傛暟
            return methods[options].apply(this, [].slice.call(arguments, 1));
        } else {
            // 璋冪敤鍒濆鍖栨柟娉�
            return methods.init.apply(this, arguments);
        }
    }
});
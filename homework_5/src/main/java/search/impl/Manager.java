package search.impl;

import manage.SearchManager;

/**
 * 请在此类中完成自己对象初始化工作，并注册
 */
public class Manager {

    static{
        // TODO:在此初始化所需组件，并将其注册到SearchManager中供主函数使用
        // SearchManager.registFileHandler(new yourFileHandler());
        // SearchManager.registSpider(new yourSpider());
        SearchManager.registSpider(new WebSpiderImpl("http://grad.udel.edu/graduate-programs/?frm-page-2654=1"));
        SearchManager.registSpider(new WebSpiderImpl("http://grad.udel.edu/graduate-programs/?frm-page-2654=2"));
        SearchManager.registSpider(new WebSpiderImpl("http://grad.udel.edu/graduate-programs/?frm-page-2654=3"));

    }
}

(ns blog-clj.admin
  (:require [hiccup.core                             :as hiccup]
            [hiccup.page                             :as page]))

(defn html
  []
  (hiccup/html (page/doctype :html5)
               [:html
                [:head
                 [:title "Jon Neale - developer, clojure lover, infrequent blog writer"]
                 [:link {:href "/bootstrap/css/bootstrap.min.css" :media "screen" :rel "stylesheet" :type "text/css"}]
                 [:link {:href "/bootstrap/css/bootstrap-responsive.min.css" :media "screen" :rel "stylesheet" :type "text/css"}]]
                [:body
                 [:div.container
                  [:div.page-header
                   [:h1 "Admin - post a new article"]]
                  [:form
                   [:label {:for "article-title"} "Article title"]
                   [:text-field {:id "article-title"}]
                   [:label {:for "article-body"} "Article body"]
                   [:text-area {:id "article-body"}]]]]]))

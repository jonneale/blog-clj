(ns blog-clj.home
  (:require [hiccup.core                             :as hiccup]
            [hiccup.page                             :as page]))
(defn html
  [visit-count]
  (hiccup/html (page/doctype :html5)
               [:html
                [:head
                 [:title "Jon Neale - developer, clojure lover, infrequent blog writer"]
                 [:link {:href "/bootstrap/css/bootstrap.min.css" :media "screen" :rel "stylesheet" :type "text/css"}]
                 [:link {:href "/bootstrap/css/bootstrap-responsive.min.css" :media "screen" :rel "stylesheet" :type "text/css"}]]
                [:body
                 [:div.container
                  [:div.page-header
                   [:h1 "Hello World"]
                   [:h2 (str "You are page view number: " visit-count)]]]]]))

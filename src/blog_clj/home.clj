(ns blog-clj.home
  (:require [hiccup.core                             :as hiccup]
            [hiccup.page                             :as page]))
(defn html
  []
  (hiccup/html (page/doctype :html5)
               [:html
                [:head
                 [:title "Jon Neale - developer, clojure lover, infrequent blog writer"]
                 [:link {:href "/bootstrap/css/bootstrap.min.css"}]]
                [:body
                 [:h1 "Hello World"]]]))

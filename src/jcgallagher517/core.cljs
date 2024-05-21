(ns jcgallagher517.core
    (:require
      [reagent.core :as r]
      [reagent.dom :as rd]
      [reitit.frontend :as rf]
      [reitit.frontend.easy :as rfe]
      [reitit.coercion.spec :as rss]
      [spec-tools.data-spec :as ds]))

;; -------------------------
;; Pages

(defn home-page []
  [:div
   [:h2 "Welcome to Reagent"]
   [:p "This is home page"]])

(defn about-page []
  [:div
   [:h2 "About me"]
   [:p "This is about page"]])

(defn physics-page []
  [:div
   [:h2 "Physics"]
   [:p "This is the physics page"]])

;; -------------------------
;; Routes

(defonce match (r/atom nil))

(defn current-page []
  [:div
   [:header
    [:h2
     [:nav
      [:div
       [:button {:type "button" :on-click #(rfe/push-state ::home)} "Home"]
       [:button {:type "button" :on-click #(rfe/push-state ::physics)} "Physics"]
       [:button {:type "button" :on-click #(rfe/push-state ::about)} "About"]]]]]
   (when @match
     (let [view (:view (:data @match))]
       [view @match]))])

(def routes
  [["/"
    {:name ::home
     :view home-page}]
   
   ["/about"
    {:name ::about
     :view about-page}]
   
   ["/physics/:phys-id"
    {:name ::physics
     :view physics-page
     :parameters {:path {:id int?}
                  :query {(ds/opt :foo) keyword?}}}]])

(defn init! []
  (rfe/start!
   (rf/router routes {:data {:coercion rss/coercion}})
   (fn [m] (reset! match m))
   {:use-fragment true})
  (rd/render [current-page] (.getElementById js/document "app")))

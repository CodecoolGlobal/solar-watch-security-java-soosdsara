import Header from "./Header";
import Footer from "./Footer";

const Page = ({ children, showLogout, contentClass }) => (
  <div className="page">
    <Header showLogout={showLogout} />
    <div className={`content ${contentClass}`}>{children}</div>
    <Footer />
  </div>
);

export default Page;

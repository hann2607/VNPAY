import { Layout } from "antd";
import { Content } from "antd/es/layout/layout";
import React from "react";
import { Outlet } from "react-router-dom";

const MainPageLayout: React.FC = () => {
  return (
    <Layout className="layout">
      <Content className="content">
        <Outlet />
      </Content>
    </Layout>
  );
};

export default MainPageLayout;
